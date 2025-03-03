package com.talkify.server.database.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
class CommentVoteModelId {
  @ManyToOne
  @JoinColumn(name = "comment_id", nullable = false)
  private CommentModel comment;

  @ManyToOne
  @JoinColumn(name = "talker_id", nullable = false)
  private TalkerModel talker;
}
