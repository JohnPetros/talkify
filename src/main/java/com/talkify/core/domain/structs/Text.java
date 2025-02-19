package com.talkify.core.domain.structs;

public record Text(String value) {
  public static Text create(String value) {
    return new Text(value);
  }
}
