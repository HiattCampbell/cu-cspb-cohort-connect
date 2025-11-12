package com.example.api.controller;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/replies")
public class ReplyController {
  @GetMapping public List<ReplyDto> list() {
    return List.of(new ReplyDto(0, 
                              0, 
                              LocalDateTime.now(), 
                              0,
                              "This is a test reply."
                               ));
  }
  @GetMapping("/{id}")
    public ReplyDto get(@PathVariable int id) {
        return new ReplyDto(id,
                            0,
                            LocalDateTime.now(),
                            0,
                            "This is a test reply."
                            );
   }
}

record ReplyDto(int id, int bulletin_id, LocalDateTime posted_on, int posted_by, String content) {}
