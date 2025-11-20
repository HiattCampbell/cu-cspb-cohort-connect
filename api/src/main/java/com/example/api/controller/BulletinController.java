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
import com.example.api.dto.BulletinDto;
import com.example.api.user.User;

import io.swagger.v3.oas.annotations.parameters.RequestBody;




@RestController
@RequestMapping("/api/v1/bulletins")
public class BulletinController {

  private final BulletinRepository bulletinRepo;

  public BulletinController(BulletinRepository bulletinRepo) {
    this.bulletinRepo = bulletinRepo;
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

  // DELETE
  @GetMapping("/{id}")
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
