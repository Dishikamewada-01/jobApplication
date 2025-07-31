package com.tech.jobApp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tech.jobApp.model.CustomUserDetails;
import com.tech.jobApp.model.Users;
import com.tech.jobApp.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> optionalUser = userRepository.findByUsername(username);

        Users user = optionalUser.orElseThrow(() -> 
            new UsernameNotFoundException("User not found with username: " + username));

        return new CustomUserDetails(user);
    }
}
