package com.example.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
  @GetMapping public List<UserDto> list() {
    return List.of(new UserDto(0, 
                              "admin", 
                              "hica3700@colorado.edu", 
                              "hiattc"
                               ));
  }
  @GetMapping("/{id}")
    public UserDto get(@PathVariable int id) {
        return new UserDto(id, "admin", "hica3700@colorado.edu", "hiattc");
   }
}

record UserDto(int id, String type, String email, String displayName) {}
