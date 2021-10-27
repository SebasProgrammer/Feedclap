package com.example.easi641.controller;

import java.util.List;

import com.example.easi641.common.EntityDtoConverter;
import com.example.easi641.common.UserType;
import com.example.easi641.dto.ProyectoDto;
import com.example.easi641.dto.ReviewDto;
import com.example.easi641.dto.UserDto;
import com.example.easi641.entities.Proyecto;
import com.example.easi641.entities.Review;
import com.example.easi641.entities.User;
import com.example.easi641.exception.FeedclapException;
import com.example.easi641.exception.InternalServerErrorException;
import com.example.easi641.services.DesarrolladorService;
import com.example.easi641.services.ReviewerService;
import com.example.easi641.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	DesarrolladorService desarrolladorService;

	@Autowired
	ReviewerService reviewerService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	EntityDtoConverter entityDtoConverter;

	@PostMapping
	public ResponseEntity<String> createUser(@RequestBody UserDto userDto) throws FeedclapException {
		User user = userService.createUser(userDto);
		if (user.getType() == UserType.DEVELOPER) {
			desarrolladorService.createDesarrollador(user);
		} else if (user.getType() == UserType.REVIEWER) {
			reviewerService.createReviewer(user);
		} else {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "UNDEFINED TYPE");
		}
		return new ResponseEntity<>(
				user.getUsername() + " has been saved as type " + UserType.parseType(user.getType()), HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<String> deleteUser(@RequestParam Long id) throws FeedclapException {
		var u = userService.findUser(id);
		if (u.isPresent()) {
			userService.deleteUser(id);
			desarrolladorService.deleteDesarrollador(id);
			// reviewerService.deleteReviewer(id);
			return new ResponseEntity<>("User " + u.get().getUsername() + " deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Id not found", HttpStatus.OK);
		}
	}

	@GetMapping
	public ResponseEntity<List<UserDto>> getResponse() throws FeedclapException {
		return new ResponseEntity<>(entityDtoConverter.convertUserToDto(userService.getAllUsers()), HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/review")
	public ResponseEntity<ReviewDto> createReview(@Valid @RequestBody ReviewDto reviewDto) {
		Review review = userService.createReview(reviewDto);
		return new ResponseEntity<>(entityDtoConverter.convertReviewToDto(review), HttpStatus.CREATED);
	}
}
