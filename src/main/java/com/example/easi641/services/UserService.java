package com.example.easi641.services;

import java.util.List;
import java.util.Optional;

import com.example.easi641.dto.ReviewDto;
import com.example.easi641.dto.UserDto;
import com.example.easi641.entities.Juego;
import com.example.easi641.entities.Review;
import com.example.easi641.entities.User;
import com.example.easi641.exception.FeedclapException;
import com.example.easi641.exception.InternalServerErrorException;
import com.example.easi641.exception.NotFoundException;
import com.example.easi641.repository.JuegoRepository;
import com.example.easi641.repository.ReviewRepository;
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

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public User createUser(UserDto userDto) throws FeedclapException {
		Long c = userRepository.countUsername(userDto.getUsername());
		if (c != 0) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR",
					userDto.getUsername() + " already in the server");
		}
		User newUser = initUser(userDto);
		return userRepository.save(newUser);
	}

	private User initUser(UserDto userDto) {
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setToken(userDto.getToken());
		user.setBirthdate(userDto.getBirthdate());
		user.setType(userDto.getType());
		return user;
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
}
