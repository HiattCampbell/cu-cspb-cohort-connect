package com.example.api.user;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
@Table(name = "users")
public class User implements UserDetails{
  public enum UserType {
    current_student,
    former_student
  }

  // id
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  
  // user type 
  @Enumerated(EnumType.STRING)
  private UserType type;

  // email 
  @Column(nullable = false, unique = true)
  private String email;

  // password
  @Column(nullable = false)
  private String password;

  // display_name
  @Column(name ="display_name", nullable = false)
  private String display_name;

  // looking_for_mentor
  @Column(name ="looking_for_mentor")
  private Boolean looking_for_mentor;

  // looking_for_mentee
  @Column(name ="looking_for_mentee")
  private Boolean looking_for_mentee;

  // mentor_to
  @ManyToOne
  @JoinColumn(name = "mentor_to")
  private User mentorTo;

  // mentored_by
  @ManyToOne
  @JoinColumn(name = "mentored_by")
  private User mentoredBy;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override public boolean isAccountNonExpired() { return true; }
  @Override public boolean isAccountNonLocked()  { return true; }
  @Override public boolean isCredentialsNonExpired()  { return true; }
  @Override public boolean isEnabled()  { return true; }

  // getters & setters

  public Long getId()  { return id; }
  public void setId(Long id) { this.id = id; }

  public UserType getType()  { return type; }
  public void setType(UserType type) { this.type = type; }

  public String getEmail()  { return email; }
  public void setEmail(String email) { this.email = email; }

  public void setPassword(String password) { this.password = password; }

  public String getDisplayName()  { return display_name; }
  public void setDisplayName(String display_name) { this.display_name = display_name; }

  public Boolean getLookingForMentor()  { return looking_for_mentor; }
  public void setLookingForMentor(Boolean looking_for_mentor) { this.looking_for_mentor = looking_for_mentor; }

  public Boolean getLookingForMentee()  { return looking_for_mentee; }
  public void setLookingForMentee(Boolean looking_for_mentee) { this.looking_for_mentee = looking_for_mentee; }

  public User getMentorTo()  { return mentorTo; }
  public void setMentorTo(User mentorTo) { this.mentorTo = mentorTo; }



}
