package com.talkify.server.api.comments.controllers;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.talkify.core.domain.dtos.CommentDto;
import com.talkify.core.interfaces.repositories.CommentsRepository;
import com.talkify.core.interfaces.repositories.TalkersRepository;
import com.talkify.core.use_cases.comments.PostCommentUseCase;

@Data
class Body {
  private String documentId;
  private CommentDto comment;
}

@CommentsController
public class PostCommentController {
  @Autowired
  private CommentsRepository commentsRepository;

  @Autowired
  private TalkersRepository talkersRepository;

  @PostMapping
  public ResponseEntity<CommentDto> handle(@RequestBody Body body) {
    var useCase = new PostCommentUseCase(commentsRepository, talkersRepository);
    var commentDto = useCase.execute(body.getComment(), body.getDocumentId());
    return ResponseEntity.status(HttpStatus.CREATED).body(commentDto);
  }
}
