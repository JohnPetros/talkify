package com.talkify.server.database.mappers;

import org.springframework.stereotype.Service;

import com.talkify.core.domain.dtos.CommentDto;
import com.talkify.core.domain.entities.Comment;
import com.talkify.server.database.models.CommentModel;

@Service
public class JpaCommentMapper {
  public Comment toEntity(CommentModel model) {
    var dto = new CommentDto()
        .setId(model.getId().toString())
        .setContent(model.getContent())
        .setPostedAt(model.getPostedAt());

    return new Comment(dto);
  }

  public CommentModel toModel(Comment entity) {
    var model = CommentModel.builder()
        .id(entity.getId())
        .content(entity.getContent().value())
        .postedAt(entity.getPostedAt().value())
        .build();

    return model;
  }

}
