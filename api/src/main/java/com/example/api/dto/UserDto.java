package com.example.api.dto;

import com.example.api.user.User;

public record UserDto(
    Long id,
    User.UserType type,
    String email,
    String displayName,
    Boolean lookingForMentee,
    Boolean lookingForMentor,
    Long mentorToId   
) {}

