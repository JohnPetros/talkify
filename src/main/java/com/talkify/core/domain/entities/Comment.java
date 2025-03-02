package com.talkify.core.domain.entities;

import com.talkify.core.domain.abstracts.Entity;
import com.talkify.core.domain.dtos.CommentDto;
import com.talkify.core.domain.dtos.CommentVoteDto;
import com.talkify.core.domain.records.Attachment;
import com.talkify.core.domain.records.Collection;
import com.talkify.core.domain.records.CommentVoteType;
import com.talkify.core.domain.records.DateTime;
import com.talkify.core.domain.records.Text;
import com.talkify.core.domain.records.Id;

public final class Comment extends Entity {
  private Text content;
  private DateTime postedAt;
  private Id talkerId;
  private Collection<Comment> replies;
  private Collection<CommentVote> votes;
  private Collection<Attachment> attachments;

  public static class CommentVote {
    private Id talkerId;
    private CommentVoteType voteType;

    public CommentVote(String talkerId, String voteType) {
      this.talkerId = Id.create(talkerId);
      this.voteType = CommentVoteType.create(voteType);
    }

    public Id getTalkerId() {
      return talkerId;
    }

    public CommentVoteType getVoteType() {
      return voteType;
    }
  }

  public Comment(CommentDto dto) {
    super(dto.id);
    content = Text.create(dto.content, "Comment content");
    postedAt = DateTime.create(dto.postedAt, "Comment posting date");
    talkerId = (dto.talkerId != null) ? Id.create(dto.talkerId, "Talker id") : Id.random();
    votes = Collection.createFrom(dto.votes,
        (commentVote) -> new CommentVote(commentVote.talkerId, commentVote.voteType));
    replies = Collection.createFrom(dto.replies, Comment::new);
    attachments = Collection.createFrom(
        dto.attachments,
        (attachment) -> Attachment.create(
            attachment.name,
            attachment.key,
            attachment.contentType));

  }

  public void vote(String voteType, String talkerId) {
    votes = votes.filter((item) -> item.getTalkerId().value().toString() != talkerId);
    votes = votes.add(new CommentVote(talkerId, voteType));
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

  public Collection<CommentVote> getVotes() {
    return votes;
  }

  public int getUpvotesCount() {
    return votes.filter((vote) -> vote.voteType.isUpvote()).size();
  }

  public int getDownvotesCount() {
    return votes.filter((vote) -> vote.voteType.isDownvote()).size();
  }

  public CommentDto getDto() {
    return new CommentDto()
        .setId(getId().toString())
        .setContent(getContent().value())
        .setPostedAt(getPostedAt().value())
        .setReplies(getReplies().map((item) -> item.getDto()).items())
        .setUpvotesCount(getUpvotesCount())
        .setDownvotesCount(getDownvotesCount())
        .setAttachments(attachments.map(Attachment::getDto).items())
        .setVotes(votes
            .map((vote) -> new CommentVoteDto()
                .setTalkerId(vote.getTalkerId().value().toString())
                .setVoteType(vote.getVoteType().value()))
            .items())
        .setTalkerId(getTalkerId().toString());
  }

}
