package com.talkify.core.domain.dtos;

import java.time.LocalDateTime;

public class CommentDto {
  public String id;
  public String content;
  public LocalDateTime postedAt;
  public TalkerDto talker;

  public CommentDto setId(String id) {
    this.id = id;
    return this;
  }

  public CommentDto setContent(String content) {
    this.content = content;
    return this;
  }

  public CommentDto setPostedAt(LocalDateTime postedAt) {
    this.postedAt = postedAt;
    return this;
  }

  public CommentDto setTalker(TalkerDto talker) {
    this.talker = talker;
    return this;
  }
}
