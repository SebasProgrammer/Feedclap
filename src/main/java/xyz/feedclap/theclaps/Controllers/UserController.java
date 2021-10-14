package xyz.feedclap.theclaps.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xyz.feedclap.theclaps.Common.EntityDtoConverter;
import xyz.feedclap.theclaps.Dtos.UserDto;
import xyz.feedclap.theclaps.Entities.User;
import xyz.feedclap.theclaps.Exceptions.FeedclapException;
import xyz.feedclap.theclaps.Services.UserService;

//@Api
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	EntityDtoConverter entityDtoConverter;

	@PostMapping
	public ResponseEntity<String> createUser(@RequestBody UserDto userDto) throws FeedclapException {
		System.out.print("[+] BEGIN OF THE LOG");
		User user = userService.createUser(userDto);
		return new ResponseEntity<>(user.getUsername() + " has been saved.", HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<String> deleteUser(@RequestParam Long id) throws FeedclapException {
		var u = userService.findUser(id);
		if (u.isPresent()) {
			userService.deleteUser(id);
			return new ResponseEntity<>("User " + u.get().getUsername() + " deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Id not found", HttpStatus.OK);
		}

	}

	@GetMapping
	public ResponseEntity<String> getResponse() throws FeedclapException {
		return new ResponseEntity<>("Hello", HttpStatus.OK);
	}
}
