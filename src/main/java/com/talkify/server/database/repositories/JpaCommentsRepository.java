package com.talkify.server.database.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.talkify.core.domain.entities.Comment;
import com.talkify.core.interfaces.repositories.CommentsRepository;
import com.talkify.server.database.mappers.JpaCommentMapper;
import com.talkify.server.database.models.CommentModel;

import java.util.UUID;

interface Jpa extends JpaRepository<CommentModel, UUID> {

}

public class JpaCommentsRepository implements CommentsRepository {
  @Autowired
  Jpa jpa;

  @Autowired
  JpaCommentMapper mapper;

  @Override
  public void add(Comment comment, String documentId) {
    var model = mapper.toModel(comment);
    model.setDocumentId(UUID.fromString(documentId));
    jpa.save(model);
  }

}
