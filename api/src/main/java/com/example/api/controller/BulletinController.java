package com.example.api.controller;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/bulletins")
public class BulletinController {
  @GetMapping public List<BulletinDto> list() {
    return List.of(new BulletinDto(0, 
                              "misc", 
                              LocalDateTime.now(), 
                              0,
                              "Test post",
                              "This is a test post."
                               ));
  }
  @GetMapping("/{id}")
    public BulletinDto get(@PathVariable int id) {
        return new BulletinDto(id, 
                              "misc", 
                              LocalDateTime.now(), 
                              0,
                              "Test post",
                              "This is a test post.");
   }
}

record BulletinDto(int id, String type, LocalDateTime posted_on, int posted_by, String title, String content) {}
