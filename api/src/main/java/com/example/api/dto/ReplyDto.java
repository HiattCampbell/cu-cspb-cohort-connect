package com.example.api.dto;

import java.time.LocalDateTime;


public record ReplyDto (int id, 
                      int bulletin_id,
                      LocalDateTime posted_on, 
                      int posted_by, 
                      String content) {
  
}
