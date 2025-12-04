package com.example.api.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.security.JWTService;
import com.example.api.user.CustomUserDetailsService;
import com.example.api.user.User;
import com.example.api.user.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public AuthController(UserRepository userRepo,
                          PasswordEncoder passwordEncoder,
                          JWTService jwtService,
                          CustomUserDetailsService userDetailsService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/register")
    public LoginResponse register(@RequestBody RegisterRequest request) {

        if (userRepo.findByEmail(request.email()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setDisplayName(request.displayName());
        user.setType(User.UserType.current_student);
        user.setLookingForMentor(false);
        user.setLookingForMentee(false);
        user.setMentorTo(null);

        userRepo.save(user);

        // automatically log in newly registered user
        String jwt = jwtService.generateToken(user);

        return new LoginResponse(jwt);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        var userDetails = userDetailsService.loadUserByUsername(request.email());

        if (!passwordEncoder.matches(request.password(), userDetails.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String jwt = jwtService.generateToken(userDetails);

        return new LoginResponse(jwt);
    }

    public record RegisterRequest(String email, String password, String displayName) {}
    public record LoginRequest(String email, String password) {}
    public record LoginResponse(String token) {}
}
