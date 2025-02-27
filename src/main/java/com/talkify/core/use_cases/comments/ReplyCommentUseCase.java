package com.talkify.core.use_cases.comments;

import com.talkify.core.domain.dtos.CommentDto;
import com.talkify.core.domain.entities.Comment;
import com.talkify.core.domain.exceptions.CommentNotFoundExeception;
import com.talkify.core.interfaces.repositories.CommentsRepository;

public class ReplyCommentUseCase {
  private CommentsRepository repository;

  public ReplyCommentUseCase(CommentsRepository repository) {
    this.repository = repository;
  }

  public CommentDto execute(CommentDto replyDto, String commentId) {
    var reply = new Comment(replyDto);
    var comment = repository.findById(commentId);
    if (comment.isEmpty())
      throw new CommentNotFoundExeception();

    repository.add(reply);

    return reply.getDto();
  }
}
