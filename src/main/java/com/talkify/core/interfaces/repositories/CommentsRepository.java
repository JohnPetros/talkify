package com.talkify.core.interfaces.repositories;

import com.talkify.core.domain.entities.Comment;
import com.talkify.core.domain.records.Id;

import java.util.Optional;

public interface CommentsRepository {
  Optional<Comment> findById(Id commentId);

  void add(Comment comment);

  void add(Comment comment, Id documentId);

  void addWithTalker(Comment comment);

  void update(Comment comment);

  void delete(Comment comment);

  Boolean hasTalker(Id talkerId);

}
