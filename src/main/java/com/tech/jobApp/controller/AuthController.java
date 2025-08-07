package com.tech.jobApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.jobApp.dto.response.UserDto;
import com.tech.jobApp.mapper.UserMapper;
import com.tech.jobApp.model.Users;
import com.tech.jobApp.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser( @RequestBody Users user) {
        user.setRole("ROLE_USER");
        Users saved = userService.registerUser(user);
        UserDto dto = UserMapper.toDto(saved);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Users user) {
        String jwt = userService.verify(user);
        return ResponseEntity.ok(jwt);
    }
}