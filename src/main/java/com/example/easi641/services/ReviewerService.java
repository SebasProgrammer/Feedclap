package com.example.easi641.services;

import java.util.List;

import com.example.easi641.entities.Reviewer;
import com.example.easi641.entities.User;
import com.example.easi641.exception.ExceptionMessageEnum;
import com.example.easi641.exception.NotFoundException;

import com.example.easi641.repository.ReviewerRepository;
import com.example.easi641.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewerService {
	@Autowired
	private ReviewerRepository reviewerRepository;

	@Autowired
	private UserRepository userRepository;

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Reviewer createReviewer(User user) {
		Reviewer reviewer = new Reviewer();
		reviewer.setId(user.getId());
		reviewer.setUsername(user.getUsername());
		return reviewerRepository.save(reviewer);
	}

	@Transactional(readOnly = true)
	public List<User> findAllReviewers() {
		return userRepository.getReviewers();
	}

	@Transactional
	public void deleteReviewer(Long reviewerId) throws RuntimeException {
		Reviewer reviewer = reviewerRepository.findById(reviewerId)
				.orElseThrow(() -> new NotFoundException(ExceptionMessageEnum.NOT_FOUND.getMessage()));
		reviewerRepository.delete(reviewer);
	}
}
