package com.tech.jobApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tech.jobApp.filter.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtAuthFilter jwtAuthFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.csrf(customizer-> customizer.disable()); // disabled csrf as using postman
		httpSecurity.authorizeHttpRequests(request -> request
		        .requestMatchers("/auth/register", "/auth/login").permitAll()
		        
		        .requestMatchers("/admin/**").hasRole("ADMIN")
		        
		        .requestMatchers("/user/**").hasAnyRole("USER" , "ADMIN")
		        
		        .requestMatchers(HttpMethod.GET, "/api/jobs/**").hasAnyRole("USER", "ADMIN")
		        
		        .requestMatchers("/api/jobs/**").hasRole("ADMIN")  // For POST, PUT, DELETE
		        
		        .requestMatchers(HttpMethod.GET, "/api/companies/**").hasAnyRole("USER", "ADMIN")
		        
		        .requestMatchers("/api/companies/**").hasRole("ADMIN")  // For POST, PUT, DELETE
		        .anyRequest().authenticated()
		);
		
		
		httpSecurity.httpBasic(Customizer.withDefaults()); // it works for postman
		httpSecurity.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		
		
		return httpSecurity.build();
	}
	
	
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder(); // secure and preferred
	 }
	 
//	 @Bean
//	 public PasswordEncoder passwordEncoder() {
//	     return NoOpPasswordEncoder.getInstance();  //  for testing only!
//	 }
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userDetailsService);
		return provider;
		
	}
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		
		return  config.getAuthenticationManager();
		
		
	}
	
}
