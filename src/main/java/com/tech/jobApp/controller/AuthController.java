package com.tech.jobApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.jobApp.dto.request.UserRegisterDto;
import com.tech.jobApp.dto.response.UserDto;
import com.tech.jobApp.model.Users;
import com.tech.jobApp.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // User registration
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserRegisterDto registerDto) {
        Users savedUser = userService.registerUser(registerDto);
        UserDto dto = new UserDto(savedUser.getUsername(), savedUser.getEmail());
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Users user) {
        String jwt = userService.verify(user);
        return ResponseEntity.ok(jwt);
    }
}