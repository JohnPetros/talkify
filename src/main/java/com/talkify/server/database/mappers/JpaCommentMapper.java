package com.talkify.server.database.mappers;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.talkify.core.domain.dtos.CommentDto;
import com.talkify.core.domain.entities.Comment;
import com.talkify.core.domain.records.CommentVoteType;
import com.talkify.core.domain.records.Id;
import com.talkify.server.database.models.CommentModel;
import com.talkify.server.database.models.CommentVoteModel;
import com.talkify.server.database.models.TalkerModel;

@Service
public class JpaCommentMapper {

  public Comment toEntity(CommentModel model) {
    var dto = new CommentDto()
        .setId(model.getId().toString())
        .setContent(model.getContent())
        .setPostedAt(model.getPostedAt())
        .setTalkerId(model.getTalker().getId().toString())
        .setReplies(
            model
                .getReplies()
                .stream()
                .map((reply) -> new CommentDto()
                    .setId(reply.getId().toString())
                    .setContent(reply.getContent().toString())
                    .setPostedAt(reply.getPostedAt())
                    .setTalkerId(model.getTalker().getId().toString()))

                .collect(Collectors.toList()));

    return new Comment(dto);
  }

  public CommentModel toModel(Comment entity) {
    var model = CommentModel.builder()
        .id(entity.getId().value())
        .content(entity.getContent().value())
        .postedAt(entity.getPostedAt().value())
        .talker(toTalkerModel(entity.getTalkerId()))
        .replies(entity.getReplies().map(this::toModel).items())
        .votes(entity
            .getVotes()
            .map((vote) -> toCommentVoteModel(vote.getVoteType(), vote.getTalkerId())).items())
        .build();

    return model;
  }

  private TalkerModel toTalkerModel(Id talkerId) {
    return TalkerModel.builder().id(talkerId.value()).build();
  }

  private CommentVoteModel toCommentVoteModel(CommentVoteType voteType, Id talkerId) {
    return CommentVoteModel
        .builder()
        .id(talkerId.value())
        .voteType(CommentVoteType.VoteType.valueOf(voteType.value().toUpperCase()))
        .build();
  }

}
