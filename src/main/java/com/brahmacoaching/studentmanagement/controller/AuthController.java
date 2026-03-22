package com.brahmacoaching.studentmanagement.controller;

import com.brahmacoaching.studentmanagement.dto.AuthResponse;
import com.brahmacoaching.studentmanagement.dto.LoginRequest;
import com.brahmacoaching.studentmanagement.dto.RegisterRequest;
import com.brahmacoaching.studentmanagement.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Public login
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    // ADMIN-only: create a new admin account
    @PostMapping("/admin/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AuthResponse> createAdmin(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.createAdmin(request));
    }

    // ADMIN-only: list all admins
    @GetMapping("/admin/list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<String>> listAdmins() {
        return ResponseEntity.ok(authService.getAllAdmins());
    }

    // ADMIN-only: delete an admin
    @DeleteMapping("/admin/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAdmin(@PathVariable UUID id) {
        authService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }

    // Bootstrap-only: kept for first-time setup, creates a USER
    // Remove or secure this in production
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
}