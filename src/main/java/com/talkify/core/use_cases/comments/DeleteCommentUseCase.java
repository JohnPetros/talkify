package com.talkify.core.use_cases.comments;

import com.talkify.core.domain.entities.Comment;
import com.talkify.core.domain.exceptions.CommentNotFoundExeception;
import com.talkify.core.domain.records.Id;
import com.talkify.core.interfaces.repositories.CommentsRepository;

public class DeleteCommentUseCase {
  private CommentsRepository repository;

  public DeleteCommentUseCase(CommentsRepository repository) {
    this.repository = repository;
  }

  public void execute(String commentId) {
    var comment = findComment(commentId);
    repository.delete(comment);
  }

  private Comment findComment(String commentId) {
    var comment = repository.findById(Id.create(commentId, "comment id"));
    if (comment.isEmpty())
      throw new CommentNotFoundExeception();

    return comment.get();
  }
}
