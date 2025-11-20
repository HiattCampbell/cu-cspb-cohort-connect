package com.example.api.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.dto.UserDto;
import com.example.api.user.User;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    // Simple test endpoint that returns a hard-coded user DTO
    @GetMapping
    public List<UserDto> list() {
        return List.of(
            new UserDto(
                0L,
                User.UserType.current_student,
                "hica3700@colorado.edu",
                "hiattc"
            )
        );
    }

    // Simple test endpoint that returns a DTO using the path id
    @GetMapping("/{id}")
    public UserDto get(@PathVariable Long id) {
        return new UserDto(
            id,
            User.UserType.current_student,
            "hica3700@colorado.edu",
            "hiattc"
        );
    }

    // Real endpoint: return the currently authenticated user from JWT
    @GetMapping("/me")
    public UserDto getCurrentUser(Authentication auth) {
        User user = (User) auth.getPrincipal();
        return UserDto.fromEntity(user);
    }
}
