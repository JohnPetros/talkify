package com.talkify.core.domain.entities;

import java.time.LocalDateTime;

import com.talkify.core.domain.abstracts.Entity;
import com.talkify.core.domain.structs.Text;

public final class Comment extends Entity {
  private Text content;

  public Comment(Dto dto) {
    super(dto.id);
    this.content = Text.create("dddj");
  }

  public String getContent() {
    return content.value();
  }

  public Dto getDto() {
    return new Comment.Dto().id(this.getId()).content(this.getContent());
  }

  static public class Dto {
    String id;
    String content;
    LocalDateTime postedAt;

    public Dto id(String id) {
      this.id = id;
      return this;
    }

    public Dto content(String content) {
      this.content = content;
      return this;
    }

    public Dto postedAt(LocalDateTime postedAt) {
      this.postedAt = postedAt;
      return this;
    }

    public Comment create() {
      return new Comment(this);
    }
  }

}
