package com.talkify.core.domain.records;

public record Email(Text text) {
  public static Email create(String value) {
    var text = Text.create(value);
    return new Email(text);
  }

  public String value() {
    return this.text.value();
  }
}
