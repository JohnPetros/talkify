package com.talkify.server.api.comments.controllers;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import com.talkify.core.interfaces.repositories.CommentsRepository;
import com.talkify.core.use_cases.comments.DeleteCommentUseCase;

@CommentsController
public class DeleteCommentController {
  @Autowired
  private CommentsRepository repository;

  @Data
  private static class Request {
    private String commentId;
  }

  @DeleteMapping
  public ResponseEntity<Void> handle(@RequestBody Request body) {
    var useCase = new DeleteCommentUseCase(repository);
    useCase.execute(body.getCommentId());
    return ResponseEntity.noContent().build();
  }
}
