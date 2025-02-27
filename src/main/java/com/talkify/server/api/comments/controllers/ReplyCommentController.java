package com.talkify.server.api.comments.controllers;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.talkify.core.domain.dtos.CommentDto;
import com.talkify.core.interfaces.repositories.CommentsRepository;
import com.talkify.core.use_cases.comments.ReplyCommentUseCase;

@CommentsController
public class ReplyCommentController {
  @Autowired
  private CommentsRepository commentsRepository;

  @Data
  private static class Body {
    CommentDto reply;
    String commentId;
  }

  @PostMapping("/reply")
  public ResponseEntity<CommentDto> handle(@RequestBody Body body) {
    var useCase = new ReplyCommentUseCase(commentsRepository);
    var commentDto = useCase.execute(body.reply, body.commentId);
    return ResponseEntity.status(HttpStatus.CREATED).body(commentDto);
  }
}
