package com.example.easi641.controller;


import com.example.easi641.common.EntityDtoConverter;
import com.example.easi641.dto.UserDto;
import com.example.easi641.entities.User;
import com.example.easi641.exception.FeedclapException;
import com.example.easi641.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) throws FeedclapException{
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
    public ResponseEntity<List<UserDto>> getResponse() throws FeedclapException {
        return new ResponseEntity<>(entityDtoConverter.convertUserToDto(userService.getAllUsers()), HttpStatus.OK);
    }
}
