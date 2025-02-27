package com.talkify.server.database.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.talkify.core.domain.entities.Comment;
import com.talkify.core.domain.records.Id;
import com.talkify.core.interfaces.repositories.CommentsRepository;
import com.talkify.server.database.mappers.JpaCommentMapper;
import com.talkify.server.database.mappers.JpaTalkerMapper;
import com.talkify.server.database.models.CommentModel;
import com.talkify.server.database.models.TalkerModel;

import java.util.Optional;
import java.util.UUID;

interface JpaCommentModelRepository extends JpaRepository<CommentModel, UUID> {

}

interface JpaTalkerModelRepository extends JpaRepository<TalkerModel, UUID> {

}

public class JpaCommentsRepository implements CommentsRepository {
  @Autowired
  JpaCommentModelRepository repository;

  @Autowired
  JpaTalkerModelRepository talkersRepository;

  @Autowired
  JpaCommentMapper commentMapper;

  @Autowired
  JpaTalkerMapper talkerMapper;

  @Override
  public void add(Comment comment, Id documentId) {
    var commentModel = commentMapper.toModel(comment);
    commentModel.setDocumentId(documentId.value());
    repository.save(commentModel);
  }

  @Override
  @Transactional
  public void add(Comment comment) {
    var model = commentMapper.toModel(comment);
    repository.save(model);
  }

  @Override
  public void update(Comment comment) {
    var model = commentMapper.toModel(comment);
    repository.save(model);
  }

  @Override
  public Optional<Comment> findById(Id commentId) {
    var model = repository.findById(commentId.value());

    if (model.isEmpty())
      return Optional.empty();

    var comment = commentMapper.toEntity(model.get());
    return Optional.of(comment);
  }

  @Override
  public void delete(Comment comment) {
    var model = commentMapper.toModel(comment);
    repository.delete(model);
  }

  @Override
  public void addWithTalker(Comment comment) {
    var talkerModel = talkerMapper.toModel(comment.getTalkerId());
    talkersRepository.save(talkerModel);

    var commentModel = commentMapper.toModel(comment);
    repository.save(commentModel);
  }

  // private Boolean addTalker(Id talkerId) {
  // var talkerModel = talkersRepository.findById(talkerId.value());
  // if (talkerModel.isEmpty()) {

  // }
  // }

}
