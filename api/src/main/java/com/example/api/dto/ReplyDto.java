package com.example.api.dto;

import java.time.LocalDateTime;


public record ReplyDto (Long id, 
                      Long bulletin_id,
                      LocalDateTime posted_on, 
                      Long posted_by, 
                      String content) {
  
}
