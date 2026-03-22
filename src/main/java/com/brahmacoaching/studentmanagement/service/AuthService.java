package com.brahmacoaching.studentmanagement.service;

import com.brahmacoaching.studentmanagement.dto.AuthResponse;
import com.brahmacoaching.studentmanagement.dto.LoginRequest;
import com.brahmacoaching.studentmanagement.dto.RegisterRequest;
import com.brahmacoaching.studentmanagement.model.User;
import com.brahmacoaching.studentmanagement.repository.UserRepository;
import com.brahmacoaching.studentmanagement.security.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil, AuthenticationManager authenticationManager,
                       UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    // ─── Public register (creates USER role only) ────────────
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new RuntimeException("Username already taken: " + request.getUsername());

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(User.Role.USER);   // public register always USER
        userRepository.save(user);

        UserDetails ud = userDetailsService.loadUserByUsername(user.getUsername());
        return new AuthResponse(jwtUtil.generateToken(ud), user.getUsername(),
                user.getRole().name(), jwtUtil.getExpiration());
    }

    // ─── Admin creates another Admin (ADMIN role only) ───────
    public AuthResponse createAdmin(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new RuntimeException("Username already taken: " + request.getUsername());

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(User.Role.ADMIN);
        userRepository.save(user);

        UserDetails ud = userDetailsService.loadUserByUsername(user.getUsername());
        return new AuthResponse(jwtUtil.generateToken(ud), user.getUsername(),
                user.getRole().name(), jwtUtil.getExpiration());
    }

    // ─── Login ────────────────────────────────────────────────
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()));

        UserDetails ud = userDetailsService.loadUserByUsername(request.getUsername());
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        return new AuthResponse(jwtUtil.generateToken(ud), user.getUsername(),
                user.getRole().name(), jwtUtil.getExpiration());
    }

    // ─── List all admins (for Settings page) ─────────────────
    public List<String> getAllAdmins() {
        return userRepository.findAll().stream()
                .filter(u -> u.getRole() == User.Role.ADMIN)
                .map(User::getUsername)
                .collect(Collectors.toList());
    }

    // ─── Delete admin by ID ───────────────────────────────────
    public void deleteAdmin(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        if (user.getRole() != User.Role.ADMIN)
            throw new RuntimeException("User is not an admin");
        userRepository.deleteById(id);
    }
}