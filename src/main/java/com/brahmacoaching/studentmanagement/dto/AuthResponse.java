package com.brahmacoaching.studentmanagement.dto;

public class AuthResponse {

    private String token;
    private String username;
    private String role;
    private long expiresIn;

    public AuthResponse(String token, String username, String role, long expiresIn) {
        this.token = token;
        this.username = username;
        this.role = role;
        this.expiresIn = expiresIn;
    }

    public String getToken() { return token; }
    public String getUsername() { return username; }
    public String getRole() { return role; }
    public long getExpiresIn() { return expiresIn; }
}