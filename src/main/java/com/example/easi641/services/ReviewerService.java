package com.example.easi641.services;

import java.util.List;

import com.example.easi641.common.UserValidator;
import com.example.easi641.dto.ReviewerDto;
import com.example.easi641.entities.Reviewer;
import com.example.easi641.entities.User;
import com.example.easi641.exception.ExceptionMessageEnum;
import com.example.easi641.exception.NotFoundException;
import com.example.easi641.repository.JuegoRepository;
import com.example.easi641.repository.ProyectoRepository;
import com.example.easi641.repository.ReviewerRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewerService {
	private final ReviewerRepository reviewerRepository;

	public ReviewerService(ReviewerRepository reviewerRepository, ProyectoRepository proyectoRepository,
			JuegoRepository juegoRepository) {
		this.reviewerRepository = reviewerRepository;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Reviewer createReviewer(ReviewerDto reviewerDto) {
		UserValidator.validateReviewer(reviewerDto);
		Reviewer reviewer = Reviewer.builder().nombre(reviewerDto.getNombre()).build();

		return reviewerRepository.save(reviewer);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Reviewer createReviewer(User user) {
		Reviewer reviewer = new Reviewer();
		reviewer.setId(user.getId());
		reviewer.setNombre(user.getName());
		reviewer.setRating(2.5f);
		return reviewerRepository.save(reviewer);
	}

	@Transactional(readOnly = true)
	public List<Reviewer> findAllRevieweres() {
		return reviewerRepository.findAll();
	}

	@Transactional
	public void deleteReviewer(Long reviewerId) {
		Reviewer reviewer = reviewerRepository.findById(reviewerId)
				.orElseThrow(() -> new NotFoundException(ExceptionMessageEnum.NOT_FOUND.getMessage()));
		reviewerRepository.delete(reviewer);
	}
}
