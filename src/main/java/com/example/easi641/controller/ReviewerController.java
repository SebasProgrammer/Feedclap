package com.example.easi641.controller;

import com.example.easi641.common.EntityDtoConverter;
import com.example.easi641.dto.*;
import com.example.easi641.entities.User;
import com.example.easi641.services.ReviewerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviewers")
public class ReviewerController {

	private final EntityDtoConverter entityDtoConverter;

	private final ReviewerService reviewerService;

	public ReviewerController(ReviewerService reviewerService, EntityDtoConverter entityDtoConverter) {
		this.reviewerService = reviewerService;
		this.entityDtoConverter = entityDtoConverter;
	}

	@GetMapping
	public ResponseEntity<List<UserDto>> findAllRevieweres() {
		List<User> revieweres = reviewerService.findAllReviewers();
		return new ResponseEntity<>(entityDtoConverter.convertUserToDto(revieweres), HttpStatus.OK);
	}
}
