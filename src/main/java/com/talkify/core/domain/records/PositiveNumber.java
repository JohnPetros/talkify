package com.talkify.core.domain.records;

import com.talkify.core.domain.exceptions.ValidationException;

public record PositiveNumber(int value) {
  public static PositiveNumber create(int value, String key) {
    if (value < 0) {
      throw new ValidationException(key, "must be greater than 0");
    }
    return new PositiveNumber(value);
  }

  public PositiveNumber increment(int value) {
    return new PositiveNumber(this.value + value);
  }

  public PositiveNumber decrement(int value) {
    return new PositiveNumber(this.value - value);
  }
}
