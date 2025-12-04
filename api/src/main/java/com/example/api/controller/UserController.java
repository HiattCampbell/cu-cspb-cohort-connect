package com.example.api.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.dto.UserDto;
import com.example.api.user.User;
import com.example.api.user.UserRepository;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserRepository userRepo;

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    // READ
    @GetMapping
    public List<UserDto> list() {
        return userRepo.findAll().stream()
                            .map(u -> new UserDto(
                                u.getId(),
                                u.getType(),
                                u.getEmail(),
                                u.getDisplayName(),
                                u.getLookingForMentee(),
                                u.getLookingForMentor(),
                                u.getMentorTo() != null ? u.getMentorTo().getId() : null
                            )).toList();
    }

    @GetMapping("/{id}")
    public UserDto get(@PathVariable Long id) {
        User u = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        return new UserDto(
            u.getId(),
            u.getType(),
            u.getEmail(),
            u.getDisplayName(),
            u.getLookingForMentee(),
            u.getLookingForMentor(),
            u.getMentorTo() != null ? u.getMentorTo().getId() : null
        );
        }

    @GetMapping("/me")
    public UserDto getCurrentUser(Authentication auth) {
        User u = (User) auth.getPrincipal();
        return new UserDto(
            u.getId(),
            u.getType(),
            u.getEmail(),
            u.getDisplayName(),
            u.getLookingForMentee(),
            u.getLookingForMentor(),
            u.getMentorTo() != null ? u.getMentorTo().getId() : null
        );
    }
}
