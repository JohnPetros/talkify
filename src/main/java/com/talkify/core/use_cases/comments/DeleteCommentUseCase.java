package com.talkify.core.use_cases.comments;

import com.talkify.core.domain.entities.Comment;
import com.talkify.core.domain.exceptions.NotFoundException;
import com.talkify.core.interfaces.repositories.CommentsRepository;

public class DeleteCommentUseCase {
  CommentsRepository repository;

  public DeleteCommentUseCase(CommentsRepository repository) {
    this.repository = repository;
  }

  public void execute(String commentId) {
    var comment = findComment(commentId);
    repository.delete(comment);
  }

  private Comment findComment(String commentId) {
    var comment = repository.findById(commentId);
    if (comment.isEmpty()) {
      throw new NotFoundException("Comment not found");
    }
    return comment.get();
  }
}
