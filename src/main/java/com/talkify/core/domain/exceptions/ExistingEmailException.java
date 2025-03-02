package com.talkify.core.domain.exceptions;

public class ExistingEmailException extends RuntimeException {
  public ExistingEmailException(String email) {
    super("Email " + email + " already exists");
  }
}
