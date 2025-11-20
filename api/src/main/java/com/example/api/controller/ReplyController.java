package com.example.api.controller;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.bulletin.Bulletin;
import com.example.api.bulletin.BulletinRepository;
import com.example.api.dto.ReplyDto;
import com.example.api.reply.Reply;
import com.example.api.reply.ReplyRepository;
import com.example.api.user.User;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
@RequestMapping("/api/v1/replies")
public class ReplyController {
  private final ReplyRepository replyRepo;
  private final BulletinRepository bulletinRepo;

  public ReplyController(ReplyRepository replyRepo, BulletinRepository bulletinRepo) {
    this.replyRepo = replyRepo;
    this.bulletinRepo = bulletinRepo;
  }

  // CREATE
  @PostMapping
  public ReplyDto create(@RequestBody ReplyDto request, Authentication auth) {
    User user = (User) auth.getPrincipal();

    Bulletin bulletin = bulletinRepo.findById(request.bulletin_id()).orElseThrow(() -> new RuntimeException("Bulletin not found"));

    Reply r = new Reply();
    r.setBulletinId(bulletin);
    r.setPostedBy(user);
    r.setPostedOn(LocalDateTime.now());
    r.setContent(request.content());

    replyRepo.save(r);

    return new ReplyDto(
      r.getId(),
      r.getBulletinId().getId(),
      r.getPostedOn(),
      r.getPostedBy().getId(),
      r.getContent()
    );
    
  }

  // READ
  @GetMapping public List<ReplyDto> list() {
    return replyRepo.findAll().stream()
                              .map(r -> new ReplyDto(
                                r.getId(),
                                r.getBulletinId().getId(),
                                r.getPostedOn(),
                                r.getPostedBy().getId(),
                                r.getContent()
                              )).toList();
  }
  @GetMapping("/{id}")
    public ReplyDto get(@PathVariable Long id) {

      Reply r = replyRepo.findById(id).orElseThrow(() -> new RuntimeException("Reply not found"));
      
      return new ReplyDto(
        r.getId(),
        r.getBulletinId().getId(),
        r.getPostedOn(),
        r.getPostedBy().getId(),
        r.getContent()
        );
   }

   // DELETE
    @GetMapping("/{id}")
      public String delete(@PathVariable Long id, Authentication auth) {
        User user = (User) auth.getPrincipal();
        Reply r = replyRepo.findById(id).orElseThrow(() -> new RuntimeException("Reply not found"));

        if (!r.getPostedBy().getId().equals(user.getId())){
          throw new RuntimeException("Can't delete someone else's reply");
        }
        
        replyRepo.delete(r);

        return "Deleted";
      }


}

