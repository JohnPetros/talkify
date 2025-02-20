package com.talkify.core.domain.exceptions;

import java.util.List;
import java.util.Map;

public class ValidationException extends AppException {
  private final Map<String, List<String>> errors;

  public ValidationException(Map<String, List<String>> errors) {
    super("Validation Exception", "");
    this.errors = errors;
  }

  public Map<String, List<String>> getErrors() {
    return errors;
  }
}
