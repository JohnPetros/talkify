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

@Data
class Body {
  private String commentId;
  private String commentContent;
}

@CommentsController
public class EditCommentController {
  @Autowired
  private CommentsRepository repository;

  @PutMapping
  public ResponseEntity<CommentDto> handle(@RequestBody Body body) {
    var useCase = new EditCommentUseCase(repository);
    var commentDto = useCase.execute(body.getCommentId(), body.getCommentContent());
    return ResponseEntity.status(HttpStatus.OK).body(commentDto);
  }
}
