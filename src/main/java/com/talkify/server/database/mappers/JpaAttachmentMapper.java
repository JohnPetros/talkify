package com.talkify.server.database.mappers;

import org.springframework.stereotype.Service;

import com.talkify.core.domain.records.Attachment;
import com.talkify.server.database.models.AttachmentModel;

@Service
public class JpaAttachmentMapper {

  public Attachment toRecord(AttachmentModel model) {
    var record = Attachment.create(
        model.getName(),
        model.getKey(),
        model.getContentType());
    return record;
  }

  public AttachmentModel toModel(Attachment attachment) {
    var model = AttachmentModel
        .builder()
        .name(attachment.name().value())
        .key(attachment.key().value())
        .contentType(attachment.contentType().value())
        .build();

    return model;
  }

}
