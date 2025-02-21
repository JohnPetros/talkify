package com.talkify.core.domain.records;

import java.time.LocalDateTime;

public record DateTime(LocalDateTime value) {
  public static DateTime create(LocalDateTime value, String key) {
    if (value instanceof LocalDateTime) {
      return new DateTime(value);
    }
    return new DateTime(LocalDateTime.now());
  }
}
