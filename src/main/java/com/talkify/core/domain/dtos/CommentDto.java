package com.talkify.core.domain.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommentDto {
  public String id;
  public String content;
  public LocalDateTime postedAt;
  public String talkerId;
  public List<CommentDto> replies = new ArrayList<>();

  public CommentDto setId(String id) {
    this.id = id;
    return this;
  }

  public CommentDto setContent(String content) {
    this.content = content;
    return this;
  }

  public CommentDto setReplies(List<CommentDto> replies) {
    this.replies = replies;
    return this;
  }

  public CommentDto setPostedAt(LocalDateTime postedAt) {
    this.postedAt = postedAt;
    return this;
  }

  public CommentDto setTalkerId(String talkerId) {
    this.talkerId = talkerId;
    return this;
  }
}
