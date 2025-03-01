package com.talkify.server.database.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comments")
public class CommentModel {
  @Id
  private UUID id;

  @Column
  private String content;

  @Column(name = "document_id", nullable = true)
  private UUID documentId;

  @ManyToOne
  @JoinColumn(name = "parent_comment_id", nullable = true)
  private CommentModel parentComment;

  @OneToMany(mappedBy = "parentComment", cascade = CascadeType.REMOVE, orphanRemoval = true)
  @Builder.Default
  private List<CommentModel> replies = new ArrayList<>();

  @ManyToOne(cascade = CascadeType.REMOVE)
  @JoinColumn(name = "talker_id")
  private TalkerModel talker;

  @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private List<CommentVoteModel> votes = new ArrayList<>();

  @Column(name = "posted_at")
  @CreationTimestamp
  private LocalDateTime postedAt;
}
