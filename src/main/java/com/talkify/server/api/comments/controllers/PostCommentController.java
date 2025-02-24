package com.talkify.server.api.comments.controllers;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import com.talkify.core.domain.dtos.CommentDto;
import com.talkify.core.interfaces.repositories.CommentsRepository;
import com.talkify.core.interfaces.repositories.TalkersRepository;
import com.talkify.core.use_cases.PostCommentUseCase;

@Data
class Body {
  String documentId;
  CommentDto comment;
}

@CommentsController
public class PostCommentController {
  @Autowired
  private CommentsRepository commentsRepository;

  @Autowired
  private TalkersRepository talkersRepository;

  @PostMapping
  public ResponseEntity<Void> handle(@RequestBody Body body) {
    var useCase = new PostCommentUseCase(commentsRepository, talkersRepository);
    useCase.execute(body.comment, body.documentId);
    return ResponseEntity.noContent().build();
  }
}
