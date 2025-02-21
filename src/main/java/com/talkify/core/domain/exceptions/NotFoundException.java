package com.talkify.core.domain.exceptions;

public class NotFoundException extends AppException {
  public NotFoundException(String message) {
    super("Not Found Exception", message);
  }
}
