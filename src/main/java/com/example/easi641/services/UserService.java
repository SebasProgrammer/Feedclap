package com.example.easi641.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.easi641.common.EntityDtoConverter;
import com.example.easi641.common.UserType;
import com.example.easi641.dto.ReviewDto;
import com.example.easi641.dto.UserDto;
import com.example.easi641.entities.Following;
import com.example.easi641.entities.Juego;
import com.example.easi641.entities.Review;
import com.example.easi641.entities.User;
import com.example.easi641.exception.ExceptionMessageEnum;
import com.example.easi641.exception.FeedclapException;
import com.example.easi641.exception.NotFoundException;
import com.example.easi641.repository.DesarrolladorRepository;
import com.example.easi641.repository.FollowingRepository;
import com.example.easi641.repository.JuegoRepository;
import com.example.easi641.repository.ReviewRepository;
import com.example.easi641.repository.ReviewerRepository;
import com.example.easi641.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private JuegoRepository juegoRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ReviewerRepository reviewerRepository;

	@Autowired
	private DesarrolladorRepository desarrolladorRepository;

	@Autowired
	private FollowingRepository followingRepository;

	@Autowired
	private EntityDtoConverter entityDtoConverter;

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public User createUser(UserDto userDto) throws Exception {
		if (userRepository.countUsername(userDto.getUsername()) != 0) {
			throw new Exception(userDto.getUsername() + " already in the server");
		}
		User newUser = new User(userDto);
		return userRepository.save(newUser);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public User updateUser(User user, UserDto userDto) throws Exception {
		if (userRepository.countUsername(userDto.getUsername()) != 0) {
			throw new Exception(userDto.getUsername() + " already in the server");
		}
		user.updateFromDto(userDto);
		return userRepository.save(user);
	}

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

	public void deleteUser(String username) {
		User u = userRepository.findByUsername(username)
				.orElseThrow(() -> new NotFoundException(ExceptionMessageEnum.NOT_FOUND.getMessage()));
		if (u.getType() == UserType.REVIEWER) {
			reviewerRepository.deleteById(u.getId());
		} else if (u.getType() == UserType.DEVELOPER) {
			desarrolladorRepository.deleteById(u.getId());
		}
		userRepository.delete(u);
	}

	@Transactional
	public Review createReview(ReviewDto reviewDto) {
		User user = userRepository.findById(reviewDto.getUserId())
				.orElseThrow(() -> new NotFoundException("Usuario not found."));

		Juego juego = juegoRepository.findById(reviewDto.getJuegoId())
				.orElseThrow(() -> new NotFoundException("Juego not found."));

		Review review = new Review();
		review.setJuego(juego);
		review.setUser(user);
		review.setDescripcion(reviewDto.getDescripcion());
		review.setPuntaje(reviewDto.getPuntaje());
		return reviewRepository.save(review);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void mkFollow(User follower, User followed) throws Exception {
		Following f = new Following(follower, followed);
		followingRepository.save(f);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<UserDto> getFollowers(Long id) throws Exception {
		List<Long> f = userRepository.getFollowers(id);
		List<UserDto> response = new ArrayList<UserDto>();
		for (Long i : f) {
			response.add(entityDtoConverter.convertUserToDto(userRepository.findById(i)
					.orElseThrow(() -> new NotFoundException("Some of the follows are incorrect"))));
		}
		return response;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<UserDto> getFollowing(Long id) throws Exception {
		List<Long> f = userRepository.getFollowing(id);
		List<UserDto> response = new ArrayList<UserDto>();
		for (Long i : f) {
			response.add(entityDtoConverter.convertUserToDto(userRepository.findById(i)
					.orElseThrow(() -> new NotFoundException("Some of the follows are incorrect"))));

		}
		return response;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Boolean loginUser(UserDto userDto) throws FeedclapException {
		if (userRepository.countUsername(userDto.getUsername()) == 1) {

			String password = userRepository.passswordofuser(userDto.getUsername());

			if (password.equals(userDto.getToken())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
