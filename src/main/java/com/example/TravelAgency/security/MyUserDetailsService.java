package com.example.TravelAgency.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.TravelAgency.entities.User;
import com.example.TravelAgency.repositories.UserRepository;
@AllArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail)//akseson repositorin dhe kthen nje objekt te plote entiet
                .orElseThrow(()->new RuntimeException("User was not found"));
        return new MyUserDetails(user);

    }
}
