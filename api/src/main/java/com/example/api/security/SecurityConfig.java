package com.example.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.api.user.CustomUserDetailsService;

// What is allowed or blocked:
// - Which endpoints require authentiction
// - Which endpoints are public
// - CORS and CSRF settings
@Configuration
public class SecurityConfig {

    private final JWTService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(JWTService jwtService, CustomUserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {})

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                            "/swagger-ui.html",
                            "/swagger-ui/**",
                            "/swagger-ui.html",
                             "/v3/api-docs/**"
                            ).permitAll()
                        
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/**").authenticated() // only authenticated users can view users
                        .requestMatchers(HttpMethod.GET, "/api/v1/bulletins/**").permitAll() //allows anyone to view bulletins
                        .requestMatchers(HttpMethod.GET, "/api/v1/replies/**").permitAll() // allows anyone to view replies

                        .anyRequest().authenticated() // requires authentication for everything else
                )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtService, userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    
}
