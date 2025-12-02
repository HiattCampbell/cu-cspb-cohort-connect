package com.example.api.controller;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.bulletin.Bulletin;
import com.example.api.bulletin.BulletinRepository;
import com.example.api.dto.BulletinDto;
import com.example.api.dto.ReplyDto;
import com.example.api.reply.ReplyRepository;
import com.example.api.user.User;



@RestController
@RequestMapping("/api/v1/bulletins")
public class BulletinController {

  private final BulletinRepository bulletinRepo;
  private final ReplyRepository replyRepo;

  public BulletinController(BulletinRepository bulletinRepo, ReplyRepository replyRepo) {
    this.bulletinRepo = bulletinRepo;
    this.replyRepo = replyRepo;
  }

  // CREATE
  @PostMapping
  public BulletinDto create(@RequestBody BulletinDto request, Authentication auth) {
    User user = (User) auth.getPrincipal();
    
    Bulletin b = new Bulletin();
    b.setType(request.type());
    b.setPostedOn(LocalDateTime.now());
    b.setPostedBy(user);
    b.setTitle(request.title());
    b.setContent(request.content());

    bulletinRepo.save(b);

    return new BulletinDto(
      b.getId(),
      b.getType(),
      b.getPostedOn(),
      b.getPostedBy().getId(),
      b.getTitle(),
      b.getContent()
    );

  }


  // READ
  @GetMapping public List<BulletinDto> list() {
    return bulletinRepo.findAll().stream()
                                .map(b -> new BulletinDto(
                                  b.getId(),
                                  b.getType(),
                                  b.getPostedOn(),
                                  b.getPostedBy().getId(),
                                  b.getTitle(),
                                  b.getContent()
                                )).toList();
  }

  @GetMapping("/{id}")
    public BulletinDto get(@PathVariable Long id) {
        Bulletin b = bulletinRepo.findById(id).orElseThrow(() -> new RuntimeException("Bulletin not found"));
   
        return new BulletinDto(
          b.getId(),
          b.getType(),
          b.getPostedOn(),
          b.getPostedBy().getId(),
          b.getTitle(),
          b.getContent());
      }

  @GetMapping("/{id}/replies")
  public List<ReplyDto> listReplies(@PathVariable Long id) {
    return replyRepo.findByBulletin_Id(id).stream()
            .map(r -> new ReplyDto(
                    r.getId(),
                    r.getBulletin().getId(),
                    r.getPostedOn(),
                    r.getPostedBy().getId(),
                    r.getContent()
            ))
            .toList();
  }

  // UPDATE

  @PutMapping("/{id}")
  public ResponseEntity<Bulletin> updateBulletint(@PathVariable Long id, @RequestBody Bulletin req) {
        Bulletin b = bulletinRepo.findById(id).orElseThrow(() -> new RuntimeException("Bulletin not found"));

        if (b == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Update the existing product's fields with the new details
        b.setTitle(req.getTitle());
        b.setContent(req.getContent());

        Bulletin updatedBulletin = bulletinRepo.save(b);
        return new ResponseEntity<>(updatedBulletin, HttpStatus.OK);
    } 


  // DELETE
  @DeleteMapping("/{id}")
      public String delete(@PathVariable Long id, Authentication auth) {
        User user = (User) auth.getPrincipal();
        Bulletin b = bulletinRepo.findById(id).orElseThrow(() -> new RuntimeException("Bulletin not found"));

        if (!b.getPostedBy().getId().equals(user.getId())){
          throw new RuntimeException("Can't delete someone else's post");
        }
        
        bulletinRepo.delete(b);

        return "Deleted";
      }
}
