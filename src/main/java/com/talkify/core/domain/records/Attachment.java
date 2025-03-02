package com.talkify.core.domain.records;

import com.talkify.core.domain.dtos.AttachmentDto;

public record Attachment(Text name, Text key, Text contentType) {
  public static Attachment create(String name, String key, String contentType) {
    return new Attachment(
        Text.create(name, "attachment name"),
        Text.create(key, "attachment key"),
        Text.create(contentType, "attachment content type"));
  }

  public AttachmentDto getDto() {
    return new AttachmentDto()
        .setName(name.value())
        .setKey(key.value())
        .setContentType(contentType.value());
  }
}
