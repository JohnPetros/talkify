package com.talkify.core.domain.entities;

import java.time.LocalDateTime;

import com.talkify.core.domain.abstracts.Entity;
import com.talkify.core.domain.dtos.CommentDto;
import com.talkify.core.domain.records.DateTime;
import com.talkify.core.domain.records.Text;

public final class Comment extends Entity {
  private Text content;
  private DateTime postedAt;
  private Talker talker;

  public Comment(CommentDto dto) {
    super(dto.id);
    this.content = Text.create(dto.content, "Conteúdo do comentário");
    this.postedAt = DateTime.create(dto.postedAt, "Data de postagem do comentário");
    this.talker = new Talker(dto.talker);
  }

  public Text getContent() {
    return content;
  }

  public DateTime getPostedAt() {
    return postedAt;
  }

  public Talker getTalker() {
    return talker;
  }

  public CommentDto getDto() {
    return new CommentDto()
        .setId(this.getId().toString())
        .setContent(this.getContent().value())
        .setPostedAt(this.getPostedAt().value())
        .setTalker(this.getTalker().getDto());
  }

}
