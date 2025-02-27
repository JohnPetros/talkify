package com.talkify.core.domain.entities;

import com.talkify.core.domain.abstracts.Entity;
import com.talkify.core.domain.dtos.CommentDto;
import com.talkify.core.domain.records.Collection;
import com.talkify.core.domain.records.DateTime;
import com.talkify.core.domain.records.Text;
import com.talkify.core.domain.records.Id;

public final class Comment extends Entity {
  private Text content;
  private DateTime postedAt;
  private Id talkerId;
  private Collection<Comment> replies;

  public Comment(CommentDto dto) {
    super(dto.id);
    content = Text.create(dto.content, "Comment content");
    postedAt = DateTime.create(dto.postedAt, "Comment posting date");
    talkerId = (dto.id != null) ? Id.create(dto.talkerId, "Talker id") : Id.random();
    replies = Collection.createFrom(dto.replies, Comment::new);
  }

  public void addReply(Comment reply) {
    replies = replies.add(reply);
  }

  public Text getContent() {
    return content;
  }

  public DateTime getPostedAt() {
    return postedAt;
  }

  public Id getTalkerId() {
    return talkerId;
  }

  public Collection<Comment> getReplies() {
    return replies;
  }

  public void edit(String newContent) {
    content = this.content.update(newContent);
  }

  public CommentDto getDto() {
    return new CommentDto()
        .setId(getId().toString())
        .setContent(getContent().value())
        .setPostedAt(getPostedAt().value())
        .setReplies(getReplies().map((item) -> item.getDto()).items())
        .setTalkerId(getTalkerId().toString());
  }

}
