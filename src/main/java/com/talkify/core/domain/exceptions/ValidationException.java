package com.talkify.core.domain.exceptions;

public class ValidationException extends AppException {
  public ValidationException(String key, String message) {
    super("Validation Exception", key + ": " + message);
  }
}
