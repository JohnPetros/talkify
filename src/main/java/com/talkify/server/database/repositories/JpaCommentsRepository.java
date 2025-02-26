package com.talkify.server.database.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.talkify.core.domain.entities.Comment;
import com.talkify.core.interfaces.repositories.CommentsRepository;
import com.talkify.server.database.mappers.JpaCommentMapper;
import com.talkify.server.database.models.CommentModel;

import java.util.Optional;
import java.util.UUID;

interface JpaCommentModelRepository extends JpaRepository<CommentModel, UUID> {

}

public class JpaCommentsRepository implements CommentsRepository {
  @Autowired
  JpaCommentModelRepository repository;

  @Autowired
  JpaCommentMapper mapper;

  @Override
  @Transactional
  public void add(Comment comment, String documentId) {
    var model = mapper.toModel(comment);
    // model.setDocumentId(UUID.fromString(documentId));
    System.out.println(model);
    repository.save(model);
  }

  @Override
  public void update(Comment comment) {
    var model = mapper.toModel(comment);
    repository.save(model);
  }

  @Override
  public Optional<Comment> findById(String commentId) {
    var model = repository.findById(UUID.fromString(commentId));

    if (model.isEmpty())
      return Optional.empty();

    var comment = mapper.toEntity(model.get());
    return Optional.of(comment);
  }

  @Override
  public void delete(Comment comment) {
    var model = mapper.toModel(comment);
    repository.delete(model);
  }

}
