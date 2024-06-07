package com.example.TravelAgency.configurations;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    private UserDetailsService userDetailsService;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf((csrf)-> csrf.disable())
                .authorizeHttpRequests((authorize)-> authorize
                        .requestMatchers(HttpMethod.GET,"api/tours/findAll").permitAll()
                        .requestMatchers(HttpMethod.GET,"api/categories/findAll").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/reviews/findAll/{tourId}").permitAll()
                        .requestMatchers(HttpMethod.GET,"api/users/findAll").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/tours/view/{tourId}").permitAll()
                        .requestMatchers(HttpMethod.GET,"api/categories/{categoryId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/reviews/findById/{tourId}/{reviewId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/users/findById/{userId}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/reviews/save/{tourId}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/tours/save").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/categories/save").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/users/save").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/tours/update/{tourId}").permitAll()
                        .requestMatchers(HttpMethod.PUT,"api/categories/update/{categoryId}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/users/update/{userId}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/tours/{tourId}").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"api/categories/delete/{categoryId}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/users/delete/{userId}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()



                        .anyRequest().authenticated()


                ).formLogin(formLogin -> formLogin
                        .permitAll()
                )
                .httpBasic(Customizer.withDefaults());


        return http.build();

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return  configuration.getAuthenticationManager();
    }

}
