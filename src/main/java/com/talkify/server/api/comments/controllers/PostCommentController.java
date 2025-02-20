package com.talkify.server.api.comments.controllers;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.talkify.core.domain.dtos.CommentDto;
import com.talkify.core.interfaces.repositories.CommentsRepository;
import com.talkify.core.interfaces.repositories.TalkersRepository;
import com.talkify.core.use_cases.PostCommentUseCase;

@Data
class Body {
  String talkerId;
  String documentId;
  CommentDto comment;
}

@CommentsController
public class PostCommentController {
  @Autowired
  CommentsRepository commentsRepository;

  @Autowired
  TalkersRepository talkersRepository;

  @PostMapping
  public ResponseEntity<Void> handle(@RequestBody Body body) {
    var useCase = new PostCommentUseCase(commentsRepository, talkersRepository);
    useCase.execute(body.comment, body.talkerId, body.documentId);
    return ResponseEntity.noContent().build();
  }
}
