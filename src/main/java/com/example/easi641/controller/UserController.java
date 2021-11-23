package com.example.easi641.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.easi641.common.EntityDtoConverter;
import com.example.easi641.dto.ReviewDto;
import com.example.easi641.dto.UserDto;
import com.example.easi641.entities.Review;
import com.example.easi641.entities.User;
import com.example.easi641.exception.ExceptionMessageEnum;
import com.example.easi641.exception.FeedclapException;
import com.example.easi641.exception.NotFoundException;
import com.example.easi641.services.DeveloperService;
import com.example.easi641.services.ReviewerService;
import com.example.easi641.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	DeveloperService developerService;

	@Autowired
	ReviewerService reviewerService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	EntityDtoConverter entityDtoConverter;

	// @GetMapping
	// public ResponseEntity<UserDto> getUser(@RequestParam Long userId) throws
	// FeedclapException {
	// return new
	// ResponseEntity<>(entityDtoConverter.convertUserToDto(userService.getAllUsers()),
	// HttpStatus.OK);
	// }

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

	@PostMapping("/follow")
	public ResponseEntity<String> followSomeone(@RequestParam String follower, @RequestParam String followed)
			throws Exception {

		User userFollowed = userService.findUser(followed)
				.orElseThrow(() -> new NotFoundException(ExceptionMessageEnum.NOT_FOUND.getMessage()));

		User userFollower = userService.findUser(follower)
				.orElseThrow(() -> new NotFoundException(ExceptionMessageEnum.NOT_FOUND.getMessage()));

		userService.mkFollow(userFollower, userFollowed);
		return new ResponseEntity<>(follower + " is now followed " + followed, HttpStatus.OK);
	}

	@PostMapping("/following")
	public ResponseEntity<List<UserDto>> getFollowing(@RequestParam String username) throws Exception {

		User user = userService.findUser(username)
				.orElseThrow(() -> new NotFoundException(ExceptionMessageEnum.NOT_FOUND.getMessage()));

		return new ResponseEntity<>(userService.getFollowing(user.getId()), HttpStatus.OK);
	}

	@PostMapping("/followers")
	public ResponseEntity<List<UserDto>> getFollowers(@RequestParam String username) throws Exception {

		User user = userService.findUser(username)
				.orElseThrow(() -> new NotFoundException(ExceptionMessageEnum.NOT_FOUND.getMessage()));

		return new ResponseEntity<>(userService.getFollowers(user.getId()), HttpStatus.OK);
	}
}
