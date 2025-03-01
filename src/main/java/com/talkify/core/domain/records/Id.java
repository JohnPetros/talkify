package com.talkify.core.domain.records;

import java.util.UUID;

import com.talkify.core.domain.exceptions.ValidationException;

public record Id(UUID value) {
  public static Id create(String value) {
    UUID uuid;

    try {
      uuid = UUID.fromString(value);
    } catch (Exception exception) {
      throw new ValidationException(value, "is not a valid UUID");
    }

    return new Id(uuid);
  }

  public static Id create(String value, String key) {
    UUID uuid;

    try {
      uuid = UUID.fromString(value);
    } catch (Exception exception) {
      throw new ValidationException(key, "is not a valid UUID");
    }

    return new Id(uuid);
  }

  public static Id random() {
    return Id.create(UUID.randomUUID().toString(), "");
  }

  public String toString() {
    return value.toString();
  }
}