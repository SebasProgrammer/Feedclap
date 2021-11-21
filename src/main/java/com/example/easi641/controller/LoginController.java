package com.example.easi641.controller;

import com.example.easi641.common.UserType;
import com.example.easi641.converters.UserConverter;
import com.example.easi641.dto.LoginRequestDto;
import com.example.easi641.dto.LoginResponseDto;
import com.example.easi641.dto.SignupRequestDto;
import com.example.easi641.dto.UserDto;
import com.example.easi641.entities.User;
import com.example.easi641.services.DeveloperService;
import com.example.easi641.services.ReviewerService;
import com.example.easi641.services.UserService;
import com.example.easi641.utils.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final UserService userService;

    private final UserConverter userConverter;

    @Autowired
    DeveloperService developerService;

    @Autowired
    ReviewerService reviewerService;

    public LoginController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }


    @PostMapping("/signup")
    public ResponseEntity<WrapperResponse<UserDto>> signup(@RequestBody SignupRequestDto request){
        User user=userService.createUser(userConverter.signup(request));
        if (user.getType() == UserType.DEVELOPER) {
            developerService.createDeveloper(user);
        } else if (user.getType() == UserType.REVIEWER) {
            reviewerService.createReviewer(user);
        }
        return new WrapperResponse<>(true,"success",userConverter.fromEntity(user))
                .createResponse();
    }

    @PostMapping("/login")
    public ResponseEntity<WrapperResponse<LoginResponseDto>> login(@RequestBody LoginRequestDto request){
        LoginResponseDto response=userService.login(request);
        return new WrapperResponse<>(true,"success",response)
                .createResponse();
    }

}