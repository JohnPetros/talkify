package com.talkify.core.domain.records;

import com.talkify.core.domain.exceptions.ValidationException;

public record Text(String value, String key) {
  public static Text create(String value, String key) {
    if (value instanceof String) {
      return new Text(value, key);
    }
    throw new ValidationException(key, "is required");
  }

  public Text update(String value) {
    return Text.create(value, key);
  }
}
