package com.talkify.core.domain.exceptions;

public class ConflictException extends AppException {
  public ConflictException(String message) {
    super("Conflict Exception", message);
  }
}
