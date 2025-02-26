package com.talkify.server.database.mappers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talkify.core.domain.dtos.CommentDto;
import com.talkify.core.domain.entities.Comment;
import com.talkify.server.database.models.CommentModel;

@Service
public class JpaCommentMapper {
  @Autowired
  JpaTalkerMapper talkersMapper;

  public Comment toEntity(CommentModel model) {
    var dto = new CommentDto()
        .setId(model.getId().toString());
    // .setContent(model.getContent())
    // .setPostedAt(model.getPostedAt());

    return new Comment(dto);
  }

  public CommentModel toModel(Comment entity) {
    var model = CommentModel.builder()
        .id(entity.getId())
        // .content(entity.getContent().value())
        // .postedAt(entity.getPostedAt().value())
        // .talker(talkersMapper.toModel(entity.getTalker()))
        .documentId(UUID.randomUUID())
        .build();

    return model;
  }

}
