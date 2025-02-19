package com.talkify.server.database.jpa.models;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "comments")
public class CommentModel {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String content;

  @CreationTimestamp
  private LocalDateTime posted_at;
}
