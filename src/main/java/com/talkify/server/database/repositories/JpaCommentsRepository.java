package com.talkify.server.database.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.talkify.core.domain.entities.Comment;
import com.talkify.core.domain.records.Attachment;
import com.talkify.core.domain.records.Collection;
import com.talkify.core.domain.records.Id;
import com.talkify.core.interfaces.repositories.CommentsRepository;
import com.talkify.server.database.mappers.JpaAttachmentMapper;
import com.talkify.server.database.mappers.JpaCommentMapper;
import com.talkify.server.database.mappers.JpaTalkerMapper;
import com.talkify.server.database.models.AttachmentModel;
import com.talkify.server.database.models.CommentModel;
import com.talkify.server.database.models.TalkerModel;

import java.util.Optional;
import java.util.UUID;

interface JpaAttachmentModelRepository extends JpaRepository<AttachmentModel, UUID> {
  public AttachmentModel findByKey(String key);
}

interface JpaCommentModelRepository extends JpaRepository<CommentModel, UUID> {

}

interface JpaTalkerModelRepository extends JpaRepository<TalkerModel, UUID> {

}

public class JpaCommentsRepository implements CommentsRepository {
  @Autowired
  JpaCommentModelRepository commentsRepository;

  @Autowired
  JpaTalkerModelRepository talkersRepository;

  @Autowired
  JpaAttachmentModelRepository attachmentsRepository;

  @Autowired
  JpaCommentMapper commentMapper;

  @Autowired
  JpaTalkerMapper talkerMapper;

  @Autowired
  JpaAttachmentMapper attachmentMapper;

  @Override
  public Collection<Comment> findMany(int page, int itemsPerPage) {
    var pageable = PageRequest.of(page - 1, itemsPerPage);
    var commentModels = commentsRepository.findAll(pageable).stream().toList();
    return Collection.createFrom(commentModels, commentMapper::toEntity);
  }

  @Override
  @Transactional
  public void add(Comment comment, Id documentId) {
    addTalker(comment.getTalkerId());
    var commentModel = commentMapper.toModel(comment);
    commentModel.setDocumentId(documentId.value());
    commentsRepository.save(commentModel);
  }

  @Override
  @Transactional
  public void add(Comment comment) {
    addTalker(comment.getTalkerId());
    var model = commentMapper.toModel(comment);
    commentsRepository.save(model);
  }

  @Override
  @Transactional
  public void update(Comment comment) {
    var model = commentMapper.toModel(comment);
    commentsRepository.save(model);
  }

  @Override
  public Optional<Comment> findById(Id commentId) {
    var model = commentsRepository.findById(commentId.value());

    if (model.isEmpty())
      return Optional.empty();

    var comment = commentMapper.toEntity(model.get());
    return Optional.of(comment);
  }

  @Override
  public void delete(Comment comment) {
    var model = commentMapper.toModel(comment);
    commentsRepository.delete(model);
  }

  @Override
  public void addReply(Comment reply, Comment comment) {
    addTalker(reply.getTalkerId());
    var replyModel = commentMapper.toModel(reply);
    var commentModel = commentMapper.toModel(comment);
    replyModel.setParentComment(commentModel);
    commentsRepository.save(replyModel);
  }

  private void addTalker(Id talkerId) {
    if (!this.hasTalker(talkerId)) {
      talkersRepository.save(talkerMapper.toModel(talkerId));
    }
  }

  public boolean hasTalker(Id talkerId) {
    var talkerModel = talkersRepository.findById(talkerId.value());
    return talkerModel.isPresent();
  }

  @Override
  public void addAttachment(Attachment attachment, Comment comment) {
    var attachmentModel = attachmentMapper.toModel(attachment);
    var commentModel = commentMapper.toModel(comment);
    attachmentModel.setComment(commentModel);
    attachmentsRepository.save(attachmentModel);
  }

  @Override
  public Optional<Attachment> findAttachment(String attachmentKey) {
    var attachmentModel = attachmentsRepository.findByKey(attachmentKey);
    if (attachmentModel == null) {
      return Optional.empty();
    }
    return Optional.of(attachmentMapper.toRecord(attachmentModel));
  }
}
