package com.talkify.server.api.comments.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.talkify.core.domain.dtos.CommentDto;
import com.talkify.core.interfaces.repositories.CommentsRepository;
import com.talkify.core.responses.PaginationResponse;
import com.talkify.core.use_cases.comments.ListCommentsUseCase;

@CommentsController
public class ListCommentsControlle {
  @Autowired
  private CommentsRepository commentsRepository;

  @GetMapping
  public ResponseEntity<PaginationResponse<CommentDto>> handle(
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "10") int itemsPerPage) {

    var useCase = new ListCommentsUseCase(commentsRepository);
    var comments = useCase.execute(page, itemsPerPage);
    var response = new PaginationResponse<CommentDto>(comments, itemsPerPage);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
