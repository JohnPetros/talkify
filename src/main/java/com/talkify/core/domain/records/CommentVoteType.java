package com.talkify.core.domain.records;

import com.talkify.core.domain.exceptions.ValidationException;

public record CommentVoteType(String value) {
  public enum VoteType {
    UPVOTE,
    DOWNVOTE,
  }

  public static CommentVoteType create(String value) {
    var text = Text.create(value.toUpperCase(), "comment vote type");

    if (text.notEqualsTo(VoteType.UPVOTE.toString()) && text.notEqualsTo(VoteType.DOWNVOTE.toString())) {
      throw new ValidationException("comment vote type", "must be upvote or downvote");
    }

    return new CommentVoteType(text.value().toLowerCase());
  }

  public boolean isUpvote() {
    return value == VoteType.UPVOTE.toString();
  }

  public boolean isDownvote() {
    return value == VoteType.DOWNVOTE.toString();
  }
}
