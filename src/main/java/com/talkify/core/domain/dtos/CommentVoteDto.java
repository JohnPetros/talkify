package com.talkify.core.domain.dtos;

public class CommentVoteDto {
  public String talkerId;
  public String voteType;

  public CommentVoteDto setTalkerId(String talkerId) {
    this.talkerId = talkerId;
    return this;
  }

  public CommentVoteDto setVoteType(String voteType) {
    this.voteType = voteType;
    return this;
  }
}
