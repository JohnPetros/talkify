package com.talkify.server.database.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import com.talkify.core.domain.records.CommentVoteType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "talkers_comments_votes")
public class CommentVoteModel {
  @Id
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "comment_id")
  private CommentModel comment;

  @ManyToOne
  @JoinColumn(name = "talker_id")
  private TalkerModel talker;

  @Enumerated(EnumType.STRING)
  @Column(name = "vote_type")
  private CommentVoteType.VoteType voteType;
}
