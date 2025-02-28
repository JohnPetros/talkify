package com.talkify.core.use_cases.comments;

import com.talkify.core.domain.dtos.CommentDto;
import com.talkify.core.domain.entities.Comment;
import com.talkify.core.domain.exceptions.CommentNotFoundExeception;
import com.talkify.core.domain.records.Id;
import com.talkify.core.interfaces.repositories.CommentsRepository;

public class EditCommentUseCase {
  private CommentsRepository repository;

  public EditCommentUseCase(CommentsRepository repository) {
    this.repository = repository;
  }

  public CommentDto execute(String commentId, String commentContent) {
    var comment = findComment(commentId);
    comment.edit(commentContent);
    repository.update(comment);
    return comment.getDto();
  }

  private Comment findComment(String commentId) {
    var comment = repository.findById(Id.create(commentId, "comment id"));
    if (comment.isEmpty()) {
      throw new CommentNotFoundExeception();
    }
    return comment.get();
  }
}
