package com.example.easi641.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.example.easi641.common.EntityDtoConverter;
import com.example.easi641.common.UserType;
import com.example.easi641.converters.UserConverter;
import com.example.easi641.dto.FollowingDto;
import com.example.easi641.dto.GameDto;
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
import org.springframework.web.bind.annotation.*;

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

	@Autowired
	UserConverter userConverter;

	@GetMapping("/{username}")
	public ResponseEntity<UserDto> getuser(@PathVariable String username){
		User user=userService.getuserbyusername(username);
		return new ResponseEntity<>(userConverter.fromEntity(user), HttpStatus.OK);
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

	@PostMapping("/follow")
	public ResponseEntity<String> followSomeone(@Valid @RequestBody FollowingDto followingDto)
			throws Exception {


		User userFollowed = userService.findUser(followingDto.getFollowing())
				.orElseThrow(() -> new NotFoundException(ExceptionMessageEnum.NOT_FOUND.getMessage()));

		User userFollower = userService.findUser(followingDto.getFollower())
				.orElseThrow(() -> new NotFoundException(ExceptionMessageEnum.NOT_FOUND.getMessage()));

		userService.mkFollow(userFollower, userFollowed);
		return new ResponseEntity<>(followingDto.getFollower() + " is now followed " + followingDto.getFollowing(), HttpStatus.OK);
	}

	@GetMapping("/following/{username}")
	public ResponseEntity<List<UserDto>> getFollowing(@PathVariable String username) throws Exception {

		return new ResponseEntity<>(userConverter.fromEntity(userService.getFollowing(username)), HttpStatus.OK);
	}

	@GetMapping("/followers/{username}")
	public ResponseEntity<List<UserDto>> getFollowers(@PathVariable String username) throws Exception {

		return new ResponseEntity<>(userConverter.fromEntity(userService.getFollowers(username)), HttpStatus.OK);
	}

	@GetMapping("/cant_following/{username}")
	public ResponseEntity<Integer> getcantFollowing(@PathVariable String username) throws Exception {

		return new ResponseEntity<>(userService.getFollowing(username).size(), HttpStatus.OK);
	}

	@GetMapping("/cant_followers/{username}")
	public ResponseEntity<Integer> getcant_Followers(@PathVariable String username) throws Exception {

		return new ResponseEntity<>(userService.getFollowers(username).size(), HttpStatus.OK);
	}

	@GetMapping("/cant_games/{username}")
	public ResponseEntity<Integer> getcant_games(@PathVariable String username) throws Exception {

		return new ResponseEntity<>(userService.getcantgames(username).size(), HttpStatus.OK);
	}
}
