package com.talkify.core.use_cases.comments;

import com.talkify.core.domain.dtos.CommentDto;
import com.talkify.core.domain.entities.Comment;
import com.talkify.core.domain.exceptions.NotFoundException;
import com.talkify.core.interfaces.repositories.CommentsRepository;

public class EditCommentUseCase {
  CommentsRepository repository;

  public EditCommentUseCase(CommentsRepository repository) {
    this.repository = repository;
  }

  public CommentDto execute(String commentId, String commentContent) {
    var comment = findComment(commentId);
    repository.update(comment);
    return comment.getDto();
  }

  private Comment findComment(String commentId) {
    var comment = repository.findById(commentId);
    if (comment.isEmpty()) {
      throw new NotFoundException("Comment not found");
    }
    return comment.get();
  }
}
