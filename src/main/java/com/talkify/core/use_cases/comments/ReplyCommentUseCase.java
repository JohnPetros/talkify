package com.talkify.core.use_cases.comments;

import com.talkify.core.domain.dtos.CommentDto;
import com.talkify.core.domain.entities.Comment;
import com.talkify.core.domain.records.Id;
import com.talkify.core.domain.exceptions.CommentNotFoundExeception;
import com.talkify.core.interfaces.repositories.CommentsRepository;

public class ReplyCommentUseCase {
  private CommentsRepository repository;

  public ReplyCommentUseCase(CommentsRepository repository) {
    this.repository = repository;
  }

  public CommentDto execute(CommentDto replyDto, String commentId) {
    var reply = new Comment(replyDto);
    var comment = findComment(commentId);
    repository.addReply(reply, comment);
    return reply.getDto();
  }

  private Comment findComment(String commentId) {
    var comment = repository.findById(Id.create(commentId, "comment id"));
    if (comment.isEmpty()) {
      throw new CommentNotFoundExeception();
    }
    return comment.get();
  }
}
