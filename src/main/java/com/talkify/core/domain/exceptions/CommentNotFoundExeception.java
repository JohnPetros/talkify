package com.talkify.core.domain.exceptions;

public class CommentNotFoundExeception extends NotFoundException {

  public CommentNotFoundExeception() {
    super("Comment not found");
  }

}
