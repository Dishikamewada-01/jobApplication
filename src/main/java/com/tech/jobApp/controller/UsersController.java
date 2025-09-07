package com.tech.jobApp.controller;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.jobApp.dto.request.UserUpdateDto;
import com.tech.jobApp.dto.response.UserDto;
import com.tech.jobApp.mapper.UserMapper;
import com.tech.jobApp.model.Users;
import com.tech.jobApp.service.UserService;
@RestController
@RequestMapping("/user")
public class UsersController {

	@Autowired
	private UserService userService;


    // Any logged-in user can see their profile
    @GetMapping("/profile")
    public String profile() {
        return "User Profile";
    }

    
    @PatchMapping("/update-profile")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserDto> updateProfile(@RequestBody UserUpdateDto updateDto, Principal principal) {
        Users updatedUser = userService.updateUserProfile(principal.getName(), updateDto);
        return ResponseEntity.ok(UserMapper.toDto(updatedUser));
    }
	
	

	
}

