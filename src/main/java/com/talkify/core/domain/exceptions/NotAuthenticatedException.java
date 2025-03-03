package com.talkify.core.domain.exceptions;

public class NotAuthenticatedException extends AppException {
  public NotAuthenticatedException(String message) {
    super("Not Authenticated exception", message);
  }

}
