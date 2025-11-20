package com.example.api.dto;

import com.example.api.user.User;

public record UserDto(Long id, User.UserType type, String email, String displayName) {
  public static UserDto fromEntity(User user) {
    return new UserDto(user.getId(), user.getType(), user.getEmail(), user.getDisplayName());
  }
}


