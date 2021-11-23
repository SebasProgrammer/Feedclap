package com.example.easi641.controller;

import com.example.easi641.common.EntityDtoConverter;
import com.example.easi641.dto.ReviewDto;
import com.example.easi641.entities.Review;
import com.example.easi641.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/review")
public class ReviewController {

	private final ReviewService reviewService;
	private final EntityDtoConverter entityDtoConverter;

	public ReviewController(ReviewService reviewService, EntityDtoConverter entityDtoConverter) {
		this.reviewService = reviewService;
		this.entityDtoConverter = entityDtoConverter;
	}

	@PostMapping
	public ResponseEntity<ReviewDto> createReview(@Valid @RequestBody ReviewDto reviewDto) {
		Review review = reviewService.createReview(reviewDto);
		return new ResponseEntity<>(entityDtoConverter.convertReviewToDto(review), HttpStatus.CREATED);
	}
	@GetMapping("/reviews/{username}")
	public ResponseEntity<List<ReviewDto>> getreviews(@PathVariable String username){
		return new ResponseEntity<>( entityDtoConverter.convertReviewToDto(reviewService.getreeviews(username)), HttpStatus.OK);
	}


	@GetMapping("/cant_reviews/{username}")
	public ResponseEntity<Integer> getcantreviews(@PathVariable String username){
		return new ResponseEntity<>( reviewService.getreeviews(username).size(), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<ReviewDto>> findAllGenres() {
		List<Review> reviews = reviewService.findAllReviews();
		return new ResponseEntity<>(entityDtoConverter.convertReviewToDto(reviews), HttpStatus.OK);
	}

	@GetMapping("/valor/{reviewId}")
	public ResponseEntity<String> getReviewCost(@PathVariable Long reviewId) {
		Float reviewCost = reviewService.getReviewCost(reviewId);
		return new ResponseEntity<>("Review #" + reviewId + " costs US$" + reviewCost, HttpStatus.OK);
	}

	@DeleteMapping("/{review_id}")
	public ResponseEntity<String> popreview(@PathVariable Long review_id) {
		reviewService.deleteReview(review_id);
		return new ResponseEntity<>("Se elimino correctamente", HttpStatus.OK);
	}
}
