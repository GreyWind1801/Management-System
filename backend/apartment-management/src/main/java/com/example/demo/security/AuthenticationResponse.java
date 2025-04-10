// ✅ Location: com.example.demo.security.AuthenticationResponse.java
package com.example.demo.security;

import com.example.demo.entities.Role;

public class AuthenticationResponse {

    private String token;
    private String email;
    private Role role;

    // ✅ Add this constructor
    public AuthenticationResponse(String token, String email, Role role) {
        this.token = token;
        this.email = email;
        this.role = role;
    }

    // ✅ Add Getters & Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
