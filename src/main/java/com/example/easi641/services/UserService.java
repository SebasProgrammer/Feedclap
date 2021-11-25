package com.example.easi641.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.easi641.common.EntityDtoConverter;
import com.example.easi641.common.UserType;
import com.example.easi641.common.UserValidator;
import com.example.easi641.converters.UserConverter;

import com.example.easi641.dto.ActivityReportDto;
import com.example.easi641.dto.LoginRequestDto;
import com.example.easi641.dto.LoginResponseDto;
import com.example.easi641.dto.ReviewDto;
import com.example.easi641.dto.UserDto;

import com.example.easi641.entities.Following;
import com.example.easi641.entities.Game;
import com.example.easi641.entities.Review;
import com.example.easi641.entities.User;
import com.example.easi641.exception.*;
import com.example.easi641.repository.DeveloperRepository;
import com.example.easi641.repository.FollowingRepository;
import com.example.easi641.repository.GameRepository;
import com.example.easi641.repository.ReviewRepository;
import com.example.easi641.repository.ReviewerRepository;
import com.example.easi641.repository.UserRepository;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserService {

	@Value("${jwt.password}")
	private String jwtSecret;

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private GameRepository juegoRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FollowingRepository followingRepository;

	@Autowired
	private ReviewerRepository reviewerRepository;

	@Autowired
	private DeveloperRepository developerRepository;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private GameRepository gameRepository;

	@Transactional
	public User createUser(User user) {
		try {
			UserValidator.validate(user);
			User existUser = userRepository.findByUsername(user.getUsername()).orElse(null);
			if (existUser != null)
				throw new IncorrectResourceRequestException("El nombre usuario ya existe");

			String encoder = passwordEncoder.encode(user.getPassword());
			user.setPassword(encoder);
			user.setNivel(1);
			user.setExp(0);
			if (user.getType() == 1) {
				user.setRankk("Desarrollador");
			} else {
				user.setRankk("Mortal");
			}
			user.setInformationn("Hola, soy nuevo en FeedClap");
			return userRepository.save(user);
		} catch (IncorrectResourceRequestException | ResourceNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public List<ActivityReportDto> getActivityReport(String username) {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found."));
		if (user.getType() == UserType.REVIEWER) {
			return reviewerRepository.getActivityReport(user.getId());
		} else if (user.getType() == UserType.DEVELOPER) {
			return developerRepository.getActivityReport(user.getId());
		}
		throw new NotFoundException("User not found.");

	}

	/*
	 * @Transactional(isolation = Isolation.READ_COMMITTED, propagation =
	 * Propagation.REQUIRED) public User updateUser(User user, UserDto userDto)
	 * throws Exception { if (userRepository.countUsername(userDto.getUsername()) !=
	 * 0) { if (user.getUsername() != userDto.getUsername()) throw new
	 * Exception(userDto.getUsername() + " already in the server"); }
	 * user.updateFromDto(userDto); return userRepository.save(user); }
	 */

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public Optional<User> findUser(Long id) {
		return userRepository.findById(id);
	}

	public Optional<User> findUser(String username) {
		return userRepository.findByUsername(username);
	}

	/*
	 * public void deleteUser(String username) { User u =
	 * userRepository.findByUsername(username) .orElseThrow(() -> new
	 * NotFoundException(ExceptionMessageEnum.NOT_FOUND.getMessage())); if
	 * (u.getType() == UserType.REVIEWER) {
	 * reviewerRepository.deleteById(u.getId()); } else if (u.getType() ==
	 * UserType.DEVELOPER) { developerRepository.deleteById(u.getId()); }
	 * userRepository.delete(u); }
	 */

	@Transactional
	public Review createReview(ReviewDto reviewDto) {
		User user = userRepository.findById(reviewDto.getUserId())
				.orElseThrow(() -> new NotFoundException("User not found."));

		Game game = juegoRepository.findById(reviewDto.getGameId())
				.orElseThrow(() -> new NotFoundException("Game not found."));

		Review review = new Review(user, game, reviewDto);
		return reviewRepository.save(review);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void mkFollow(User follower, User followed) throws Exception {
		Following f = new Following(follower, followed);
		followingRepository.save(f);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<User> getFollowers(String username) throws Exception {
		List<String> f = followingRepository.getFollowers(username);
		List<User> response = new ArrayList<>();
		for (String i : f) {
			response.add(userRepository.findByUsername(i)
					.orElseThrow(() -> new NotFoundException("Some of the follows are incorrect")));
		}
		return response;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<User> getFollowing(String username) throws Exception {
		List<String> f = followingRepository.getFollowing(username);
		List<User> response = new ArrayList<>();
		for (String i : f) {
			response.add(userRepository.findByUsername(i)
					.orElseThrow(() -> new NotFoundException("Some of the follows are incorrect")));
		}
		return response;
	}

	public LoginResponseDto login(LoginRequestDto request) {
		try {
			User user = userRepository.findByUsername(request.getUsername())
					.orElseThrow(() -> new IncorrectResourceRequestException("Usuario o password incorrecto"));

			if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
				throw new IncorrectResourceRequestException("Usuario o password incorrectos");

			String token = createToken(user);

			return new LoginResponseDto(userConverter.fromEntity(user), token);

		} catch (IncorrectResourceRequestException | ResourceNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	public String createToken(User user) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + (1000 * 60 * 60));

		return Jwts.builder().setSubject(user.getUsername()).setIssuedAt(now).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (UnsupportedJwtException e) {
			log.error("JWT in a particular format/configuration that does not match the format expected");
		} catch (MalformedJwtException e) {
			log.error(" JWT was not correctly constructed and should be rejected");
		} catch (SignatureException e) {
			log.error("Signature or verifying an existing signature of a JWT failed");
		} catch (ExpiredJwtException e) {
			log.error("JWT was accepted after it expired and must be rejected");
		}
		return false;
	}

	public String getUsernameFromToken(String jwt) {
		try {
			return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().getSubject();
		} catch (Exception e) {
			throw new IncorrectResourceRequestException("Invalid Token");
		}
	}

	public List<Game> getcantgames(String username) {
		User user = userRepository.getByuserName(username);
		List<Game> games = gameRepository.getgamesofuser(user.getId());
		return games;
	}

	@Transactional(readOnly = true)
	public User getuserbyusername(String userName) {
		return userRepository.getByuserName(userName);
	}

	@Transactional(readOnly = true)
	public User getuserbyid(Long userid) {
		return userRepository.getById(userid);
	}

	public void updateuser(Long userid, UserDto userDto) {
		User user =userRepository.getById(userid);
		user.setInformationn(userDto.getInformation());
		user.setRankk(userDto.getRank());
		user.setExp(userDto.getExp());
		user.setNivel(userDto.getNivel());
		user.setName(userDto.getName());
		userRepository.save(user);
	}
}
