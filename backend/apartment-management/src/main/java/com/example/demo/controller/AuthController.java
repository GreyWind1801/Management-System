package com.example.demo.controller;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.AuthenticationRequest;
import com.example.demo.security.AuthenticationResponse;
import com.example.demo.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private com.example.demo.security.CustomUserDetailsService userDetailsService;

    // 🔐 LOGIN Endpoint
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("Invalid Credentials");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        User user = userRepository.findByEmail(request.getEmail()).get();

        return ResponseEntity.ok(new AuthenticationResponse(jwt, user.getEmail(), user.getRole()));
    }

    // 👤 REGISTER Endpoint
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("User already exists with this email.");
        }

        // 🛡️ Encrypt the password
        user.setPassword(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode(user.getPassword()));
        
        // 👤 Set default role if not provided
        if (user.getRole() == null || user.getRole().toString().isEmpty()) {
        	user.setRole(Role.valueOf("RESIDENT"));
        }

        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }
}
