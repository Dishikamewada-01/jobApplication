package com.tech.jobApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tech.jobApp.model.Users;
import com.tech.jobApp.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtService jwtService;
		
	
	public Users registerUser(Users user) {
		 String encodedPassword = passwordEncoder.encode(user.getPassword());
	     user.setPassword(encodedPassword);
		return userRepository.save(user);
	}

	
	public String verify(Users user) {
		Authentication authentication= authManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		
		if(authentication.isAuthenticated()) {
			return jwtService.generateTokens(user.getUsername());
		}
		
		return "fail";
	}
	
	
	
	// Get all users
	public List<Users> getAllUsers(){
		return userRepository.findAll();
	}
	
	
	// Delete user by ID
	public void deleteUserById(int id) {
	    userRepository.deleteById(id);
	}
	
}
