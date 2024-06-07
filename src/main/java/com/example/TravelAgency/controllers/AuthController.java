package com.example.TravelAgency.controllers;

import com.example.TravelAgency.dto.LoginForm;
import com.example.TravelAgency.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginForm loginForm) {
        if (authService.authenticate(loginForm.getUsername(), loginForm.getPassword())) {
            // Authentication successful
            return ResponseEntity.ok("Login successful");
        } else {
            // Authentication failed
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

}