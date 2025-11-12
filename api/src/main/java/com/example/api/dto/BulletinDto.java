package com.example.api.dto;

import java.time.LocalDateTime;

public record BulletinDto(int id, 
                          String type, 
                          LocalDateTime posted_on, 
                          int posted_by, 
                          String title, 
                          String content) {}
