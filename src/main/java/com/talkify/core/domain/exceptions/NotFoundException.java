package com.talkify.core.domain.exceptions;

public class NotFoundException extends AppException {
  private final String message;

  public NotFoundException(String message) {
    super("Not Found Exception", message);
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

}
