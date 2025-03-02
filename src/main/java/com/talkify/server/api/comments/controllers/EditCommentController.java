package com.talkify.server.api.comments.controllers;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.talkify.core.domain.dtos.CommentDto;
import com.talkify.core.interfaces.repositories.CommentsRepository;
import com.talkify.core.use_cases.comments.EditCommentUseCase;

@CommentsController
public class EditCommentController {
  @Autowired
  private CommentsRepository repository;

  @Data
  private static class Request {
    String commentId;
    String commentContent;
  }

  @PutMapping
  public ResponseEntity<CommentDto> handle(@RequestBody Request body) {
    var useCase = new EditCommentUseCase(repository);
    var commentDto = useCase.execute(body.commentId, body.commentContent);
    return ResponseEntity.status(HttpStatus.OK).body(commentDto);
  }
}
