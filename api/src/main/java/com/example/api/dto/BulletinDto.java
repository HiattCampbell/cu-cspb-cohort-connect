package com.example.api.dto;

import java.time.LocalDateTime;

import com.example.api.bulletin.Bulletin;

public record BulletinDto(Long id, 
                          Bulletin.BulletinType type,
                          LocalDateTime posted_on, 
                          Long posted_by, 
                          String title, 
                          String content) {}
