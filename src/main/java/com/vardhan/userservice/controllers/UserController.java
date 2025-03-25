package com.vardhan.userservice.controllers;

import com.vardhan.userservice.dtos.*;
import com.vardhan.userservice.models.Token;
import com.vardhan.userservice.models.User;
import com.vardhan.userservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public SignupResponseDto signUp(@RequestBody SignupRequestDto signupRequestDto) {
        UserService userService = this.userService;
        User user = userService.signup(signupRequestDto.getName(), signupRequestDto.getEmail(), signupRequestDto.getPassword());
        return SignupResponseDto.from(user);
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {

        Token token = userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        return LoginResponseDto.from(token);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(LogoutRequestDto logoutRequestDto) {
        return null;
    }

    @GetMapping("/validate/{token}")
    public ResponseEntity<Boolean> validateToken(@PathVariable("token") String token) {
        User user = userService.validateToken(token);
        ResponseEntity<Boolean> responseEntity;
        if (user == null) {
            responseEntity = new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        } else {

            responseEntity = new ResponseEntity<>(true, HttpStatus.OK);
         }
        return responseEntity;
       }



}
