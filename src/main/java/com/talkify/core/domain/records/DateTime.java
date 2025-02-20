package com.talkify.core.domain.records;

import java.time.LocalDateTime;

import com.talkify.core.domain.exceptions.ValidationException;

public record DateTime(LocalDateTime value) {
  public static DateTime create(LocalDateTime value, String key) {
    if (value instanceof LocalDateTime) {
      return new DateTime(value);
    }
    throw new ValidationException(key, "É obrigatório");
  }
}
