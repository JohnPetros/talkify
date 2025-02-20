package com.talkify.core.domain.records;

import java.util.LinkedHashMap;
import java.util.List;

import com.talkify.core.domain.exceptions.ValidationException;

public record Text(String value) {
  public static Text create(String value) {
    if (value instanceof String) {
      return new Text(value);
    }
    throw new ValidationException(null);
  }
}
