package com.tech.jobApp.controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/user")
public class UsersController {

	


    // Any logged-in user can see their profile
    @GetMapping("/profile")
    public String profile() {
        return "User Profile";
    }

    
	
	

	
}

