package com.tech.jobApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.tech.jobApp.dto.response.UserDto;
import com.tech.jobApp.mapper.UserMapper;
import com.tech.jobApp.model.Users;
import com.tech.jobApp.service.UserService;

@RestController
public class UsersController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/user/profile")
    public String profile() {
        return "User Profile";
    }
	
	@PostMapping("/register")
	public UserDto registerUser(@RequestBody Users user) {
		 user.setRole("ROLE_USER"); // default role
		Users saved = userService.registerUser(user);
	    UserDto dto = UserMapper.toDto(saved);
		return dto;
	}
	
	
	@PostMapping("/login")
	public String loginUser(@RequestBody Users user) {
		return userService.verify(user);
	}
	
}

