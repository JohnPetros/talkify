package com.talkify.server.api.comments.controllers;

import org.springframework.web.bind.annotation.GetMapping;

import com.talkify.core.domain.entities.Comment;

@CommentsController
public class PostCommentController {

  @GetMapping("path")
  public String handle() {
    var dto = new Comment.Dto().id("gg").content("dkfjf").postedAt(null);

    var comment = new Comment(dto);

    comment.getContent();

    return new String("Oi");
  }
}
