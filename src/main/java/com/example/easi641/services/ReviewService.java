package com.example.easi641.services;

import java.util.List;

import com.example.easi641.dto.ReviewDto;
import com.example.easi641.entities.Game;
import com.example.easi641.entities.Review;
import com.example.easi641.entities.User;
import com.example.easi641.exception.ExceptionMessageEnum;
import com.example.easi641.exception.NotFoundException;
import com.example.easi641.repository.GameRepository;
import com.example.easi641.repository.ReviewRepository;
import com.example.easi641.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {
	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GameRepository gameRepository;

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Review createReview(ReviewDto reviewDto) {

		Game game = gameRepository.findById(reviewDto.getGameId())
				.orElseThrow(() -> new NotFoundException("Game not found."));

		User user = userRepository.findById(reviewDto.getUserId())
				.orElseThrow(() -> new NotFoundException("User not found."));

		Review review = new Review(user, game, reviewDto);
		return reviewRepository.save(review);
	}

	@Transactional(readOnly = true)
	public List<Review> findAllReviews() {
		return reviewRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Float getReviewCost(Long reviewId) {
		Review review = reviewRepository.findById(reviewId)
				.orElseThrow(() -> new NotFoundException(ExceptionMessageEnum.NOT_FOUND.getMessage()));
		Float reviewCost = review.getCost();
		return reviewCost;
	}

	@Transactional
	public void deleteReview(Long reviewId) {
		Review review = reviewRepository.findById(reviewId)
				.orElseThrow(() -> new NotFoundException(ExceptionMessageEnum.NOT_FOUND.getMessage()));
		reviewRepository.delete(review);
	}

	@Transactional(readOnly = true)
	public List<Review> getreeviews(String username) {
		User usuario= userRepository.findByUsername(username)
				.orElseThrow(() -> new NotFoundException(ExceptionMessageEnum.NOT_FOUND.getMessage()));
		List<Review> reviews = reviewRepository.getreviewsbyuser(usuario.getId());
		return reviews;
	}
}
