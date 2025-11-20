package com.example.api.reply;

import java.time.LocalDateTime;

import com.example.api.bulletin.Bulletin;
import com.example.api.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "replies")
public class Reply {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "bulletin_id", nullable = false)
  private Bulletin bulletin;

  @Column(name = "posted_on", nullable = false)
  private LocalDateTime postedOn;

  @ManyToOne
  @JoinColumn(name = "posted_by", nullable = false)
  private User postedBy;


  @Column(length = 300)
  private String content;

  // getters & setters

  public Long getId()  { return id; }
  public void setId(Long id) { this.id = id; }

  public Bulletin getBulletin()  { return bulletin; }
  public void setBulletin(Bulletin bulletin) { this.bulletin = bulletin; }

  public LocalDateTime getPostedOn()  { return postedOn; }
  public void setPostedOn(LocalDateTime postedOn) { this.postedOn = postedOn; }

  public User getPostedBy()  { return postedBy; }
  public void setPostedBy(User postedBy) { this.postedBy = postedBy; }

  public String getContent()  { return content; }
public void setContent(String content) { this.content = content; }
  
}
