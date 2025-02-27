package com.talkify.core.use_cases.comments;

import com.talkify.core.domain.dtos.CommentDto;
import com.talkify.core.domain.entities.Comment;
import com.talkify.core.domain.records.Id;
import com.talkify.core.interfaces.repositories.CommentsRepository;

public class PostCommentUseCase {
  private CommentsRepository repository;

  public PostCommentUseCase(CommentsRepository repository) {
    this.repository = repository;
  }

  public CommentDto execute(CommentDto commentDto, String documentId) {
    var comment = new Comment(commentDto);
    comment.getTalkerId();
    if (this.repository.hasTalker(comment.getTalkerId())) {

    }

    repository.add(comment, Id.create(documentId, "document id"));
    return comment.getDto();
  }
}
