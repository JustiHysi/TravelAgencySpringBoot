package com.example.TravelAgency.services;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.TravelAgency.entities.User;
import com.example.TravelAgency.repositories.UserRepository;

import java.util.Optional;

@Service
@EnableWebSecurity
@Configuration

public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Constructor injection for UserRepository and PasswordEncoder


    public boolean authenticate(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Use injected PasswordEncoder to check the encoded password
            if (passwordEncoder.matches(password, user.getPassword())) {
                // Passwords match, authentication successful
                return true;
            }
        }
        // Authentication failed
        return false;
    }
}