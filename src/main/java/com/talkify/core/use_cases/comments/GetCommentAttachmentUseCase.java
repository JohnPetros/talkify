package com.talkify.core.use_cases.comments;

import com.talkify.core.domain.dtos.AttachmentDto;
import com.talkify.core.domain.exceptions.AttachmentNotFoundException;
import com.talkify.core.interfaces.repositories.CommentsRepository;

public class GetCommentAttachmentUseCase {
  private CommentsRepository repository;

  public GetCommentAttachmentUseCase(CommentsRepository repository) {
    this.repository = repository;
  }

  public AttachmentDto execute(String attachmentKey) {
    var attachment = repository.findAttachment(attachmentKey);
    if (attachment.isEmpty()) {
      throw new AttachmentNotFoundException();
    }
    return attachment.get().getDto();
  }
}
