package com.example.demo.security;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // Step 1: This method is called by Spring Security to load a user by username/email
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Step 2: Find the user from the database
        User user = userRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().name());
        // Step 3: Return a Spring Security compatible UserDetails object
        return new org.springframework.security.core.userdetails.User(
            user.getEmail(), // username
            user.getPassword(), // encrypted password
            List.of(authority) // roles/authorities - can be added later
        );
    }
}
