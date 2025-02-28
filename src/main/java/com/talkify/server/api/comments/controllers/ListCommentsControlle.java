package com.talkify.server.api.comments.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@CommentsController
public class ListCommentsControlle {
  @GetMapping
  public ResponseEntity<List<String>> handle() {
    return ResponseEntity.status(HttpStatus.CREATED).body(List.of());
  }
}
