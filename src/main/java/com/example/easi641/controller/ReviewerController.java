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
@RequestMapping("/revieweres")
public class ReviewerController {

	private final EntityDtoConverter entityDtoConverter;

	private final ReviewerService reviewerService;

	public ReviewerController(ReviewerService reviewerService, EntityDtoConverter entityDtoConverter) {
		this.reviewerService = reviewerService;
		this.entityDtoConverter = entityDtoConverter;
	}

	@GetMapping
	public ResponseEntity<List<UserDto>> findAllRevieweres() {
		List<User> revieweres = reviewerService.findAllRevieweresAsUsers();
		return new ResponseEntity<>(entityDtoConverter.convertUserToDto(revieweres), HttpStatus.OK);
	}

	// @PutMapping("/{reviewerId}")
	// public ResponseEntity<JuegoResponse> popGame(@PathVariable Long
	// reviewerId) {
	// reviewerService.deleteReviewer(reviewerId);
	// return new ResponseEntity<>(HttpStatus.OK);
	// }

	// @ResponseStatus(HttpStatus.OK)
	// @PostMapping("/proyecto")
	// public ResponseEntity<ProyectoDto> createProyecto(@Valid @RequestBody
	// ProyectoDto proyectoDto) {
	// Proyecto proyecto = reviewerService.createProyecto(proyectoDto);
	// return new
	// ResponseEntity<>(entityDtoConverter.convertProyectoToDto(proyecto),
	// HttpStatus.CREATED);
	// }
}
