package com.example.api.bulletin;

import java.time.LocalDateTime;

import com.example.api.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bulletins")
public class Bulletin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private BulletinType type;

    @Column(name = "posted_on", nullable = false)
    private LocalDateTime postedOn;

    @ManyToOne
    @JoinColumn(name = "posted_by", nullable = false)
    private User postedBy;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(length = 300)
    private String content;

    public enum BulletinType {
        event,
        announcement,
        question,
        misc
    }

    // getters & setters

    public Long getId()  { return id; }
    public void setId(Long id) { this.id = id; }

    public BulletinType getType()  { return type; }
    public void setType(BulletinType type) { this.type = type; }

    public LocalDateTime getPostedOn()  { return postedOn; }
    public void setPostedOn(LocalDateTime postedOn) { this.postedOn = postedOn; }

    public User getPostedBy()  { return postedBy; }
    public void setPostedBy(User postedBy) { this.postedBy = postedBy; }

    public String getTitle()  { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent()  { return content; }
    public void setContent(String content) { this.content = content; }



}