package com.project.jobApplicationTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.bind.annotation.*;

import com.project.jobApplicationTracker.dto.LoginRequestDTO;
import com.project.jobApplicationTracker.dto.LoginResponseDTO;
import com.project.jobApplicationTracker.dto.RegisterRequestDTO;
import com.project.jobApplicationTracker.dto.UserDTO;
import com.project.jobApplicationTracker.model.User;
import com.project.jobApplicationTracker.service.ApplicationService;
import com.project.jobApplicationTracker.service.CustomUserDetailsService;
import com.project.jobApplicationTracker.service.JwtService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody RegisterRequestDTO request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        User saved = applicationService.registraUser(user);
        return ResponseEntity.ok(new UserDTO(saved.getId(), saved.getUsername()));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        try {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getUsername());
            
            if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            // genera token
            User user = new User();
            user.setUsername(request.getUsername());
            String token = jwtService.generaToken(user);
            return ResponseEntity.ok(new LoginResponseDTO(request.getUsername(), token));

        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}