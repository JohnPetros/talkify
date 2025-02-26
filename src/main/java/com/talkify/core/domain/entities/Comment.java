package com.talkify.core.domain.entities;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.talkify.core.domain.abstracts.Entity;
import com.talkify.core.domain.dtos.CommentDto;
import com.talkify.core.domain.records.DateTime;
import com.talkify.core.domain.records.Text;

public final class Comment extends Entity {
  private Text content;
  private DateTime postedAt;
  private UUID talkerId;
  private List<Comment> replies;

  public Comment(CommentDto dto) {
    super(dto.id);
    this.content = Text.create(dto.content, "Comment content");
    this.postedAt = DateTime.create(dto.postedAt, "Comment posting date");
    this.replies = dto.replies.stream().map(Comment::new).collect(Collectors.toList());
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

  public List<Comment> getReplies() {
    return replies;
  }

  public Talker edit(String content) {
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
