package com.talkify.core.use_cases.comments;

import com.talkify.core.domain.dtos.AttachmentDto;
import com.talkify.core.domain.entities.Comment;
import com.talkify.core.domain.exceptions.CommentNotFoundExeception;
import com.talkify.core.domain.records.Attachment;
import com.talkify.core.domain.records.Id;
import com.talkify.core.interfaces.providers.StorageProvider;
import com.talkify.core.interfaces.repositories.CommentsRepository;

public class UploadCommentAttachmentUseCase {
  private CommentsRepository repository;
  private StorageProvider storageProvider;

  public UploadCommentAttachmentUseCase(CommentsRepository repository, StorageProvider storageProvider) {
    this.repository = repository;
    this.storageProvider = storageProvider;
  }

  public String execute(
      String commentId,
      String attachmentName,
      String attachmentContentType,
      byte[] attachmentBytes) {

    var comment = findComment(commentId);
    var attachmentKey = storageProvider.uploadFile(attachmentName, attachmentContentType, attachmentBytes);
    var attachment = Attachment.create(attachmentName, attachmentKey, attachmentContentType);

    repository.addAttachment(attachment, comment);
    return attachment.key().value();
  }

  private Comment findComment(String commentId) {
    var comment = repository.findById(Id.create(commentId, "comment id"));
    if (comment.isEmpty()) {
      throw new CommentNotFoundExeception();
    }
    return comment.get();
  }
}
