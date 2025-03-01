package com.talkify.server.api.comments.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.talkify.core.domain.dtos.CommentDto;
import com.talkify.core.interfaces.repositories.CommentsRepository;
import com.talkify.core.use_cases.comments.VoteCommentUseCase;

import lombok.Data;

@CommentsController
public class VoteCommentController {
  @Autowired
  private CommentsRepository repository;

  @Data
  private static class Body {
    String commentVote;
    String commentId;
    String talkerId;
  }

  @PatchMapping("/vote")
  public ResponseEntity<CommentDto> handle(@RequestBody Body body) {
    var useCase = new VoteCommentUseCase(repository);
    var commentDto = useCase.execute(body.commentVote, body.commentId, body.talkerId);
    return ResponseEntity.status(HttpStatus.OK).body(commentDto);
  }
}
