package com.talkify.core.interfaces.repositories;

import com.talkify.core.domain.entities.Comment;

public interface CommentsRepository {
  void add(Comment comment, String documentId);
}
