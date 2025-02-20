package com.talkify.core.domain.records;

import java.time.LocalDateTime;

public record DateTime(LocalDateTime value) {
  public static DateTime create(LocalDateTime value) {
    return new DateTime(value);
  }
}
