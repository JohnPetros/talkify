package com.talkify.core.interfaces.repositories;

import com.talkify.core.domain.entities.Comment;

import java.util.Optional;

public interface CommentsRepository {
  Optional<Comment> findById(String commentId);

  void add(Comment comment, String documentId);

  void update(Comment comment);

  void delete(Comment comment);
}
