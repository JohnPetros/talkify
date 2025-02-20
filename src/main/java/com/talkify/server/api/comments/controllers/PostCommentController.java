package com.talkify.server.api.comments.controllers;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;

import com.talkify.core.domain.dtos.CommentDto;
import com.talkify.core.interfaces.repositories.CommentsRepository;
import com.talkify.core.interfaces.repositories.TalkersRepository;
import com.talkify.core.use_cases.PostCommentUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Data
class Body {
  String talkerId;
  String documentId;
  CommentDto comment;
}

@RestController
@RequestMapping("/comments")
public class PostCommentController {
  @Autowired
  CommentsRepository commentsRepository;

  @Autowired
  TalkersRepository talkersRepository;

  @GetMapping
  public ResponseEntity<Void> handle(@RequestBody Body body) {
    var useCase = new PostCommentUseCase(commentsRepository, talkersRepository);
    useCase.execute(body.comment, body.talkerId, body.documentId);
    return ResponseEntity.noContent().build();
  }
}
