package com.talkify.core.domain.records;

import com.talkify.core.domain.exceptions.ValidationException;

public record Text(String value) {
  public static Text create(String value, String key) {
    if (value instanceof String) {
      return new Text(value);
    }
    throw new ValidationException(key, "É obrigatório");
  }
}
