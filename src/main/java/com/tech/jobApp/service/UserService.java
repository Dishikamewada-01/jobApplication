package com.tech.jobApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tech.jobApp.dto.request.UserUpdateDto;
import com.tech.jobApp.dto.response.UserDto;
import com.tech.jobApp.mapper.CompanyMapper;
import com.tech.jobApp.mapper.UserMapper;
import com.tech.jobApp.model.Company;
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
		
	
	// Register User
	public Users registerUser(Users user) {
		 String encodedPassword = passwordEncoder.encode(user.getPassword());
	     user.setPassword(encodedPassword);
		return userRepository.save(user);
	}

	
	// For User Verification
	public String verify(Users user) {
		Authentication authentication= authManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		
		if(authentication.isAuthenticated()) {
			return jwtService.generateTokens(user.getUsername());
		}
		
		return "fail";
	}
	
	
	
	// Get all users
	public List<UserDto> getAllUsers(int page , int size){
		Pageable pageable=PageRequest.of(page, size);
		Page<Users> usersPage= userRepository.findAll(pageable);
		List<UserDto> userDtos= new ArrayList<>();
		for (Users user : usersPage.getContent()) {
	        UserDto dto = UserMapper.toDto(user);
	        userDtos.add(dto);
	    }
		return userDtos;
	}
	
	
	// Get User By Username
	public UserDto getUserByUsername(String username) {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
        return UserMapper.toDto(user);
    }
	
	
	// Delete By user id
	public void deleteUserById(Long id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        userRepository.delete(user);
    }
	
	
	// Update User
	public Users updateUserProfile(String currentUsername, UserUpdateDto updateDto) {
	    Users existingUser = userRepository.findByUsername(currentUsername)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    if (updateDto.getUsername() != null && !updateDto.getUsername().isBlank()) {
	        existingUser.setUsername(updateDto.getUsername());
	    }
	    if (updateDto.getEmail() != null && !updateDto.getEmail().isBlank()) {
	        existingUser.setEmail(updateDto.getEmail());
	    }
	    if (updateDto.getPassword() != null && !updateDto.getPassword().isBlank()) {
	        existingUser.setPassword(passwordEncoder.encode(updateDto.getPassword()));
	    }

	    return userRepository.save(existingUser);
	}
	
}
