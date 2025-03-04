package com.talkify.core.interfaces.repositories;

import com.talkify.core.domain.entities.Comment;
import com.talkify.core.domain.records.Attachment;
import com.talkify.core.domain.records.Collection;
import com.talkify.core.domain.records.Id;

import java.util.Optional;

public interface CommentsRepository {
  Collection<Comment> findMany(int page, int itemsPerPage);

  Optional<Comment> findById(Id commentId);

  Optional<Attachment> findAttachment(String attachmentKey);

  void add(Comment comment);

  void add(Comment comment, Id documentId);

  void addAttachment(Attachment attachment, Comment comment);

  void addReply(Comment reply, Comment comment);

  void update(Comment comment);

  void delete(Comment comment);

  boolean hasTalker(Id talkerId);
}
