package com.talkify.core.use_cases.comments;

import java.util.List;

import com.talkify.core.domain.dtos.CommentDto;
import com.talkify.core.interfaces.repositories.CommentsRepository;

public class ListCommentsUseCase {
  private CommentsRepository repository;

  public ListCommentsUseCase(CommentsRepository repository) {
    this.repository = repository;
  }

  public List<CommentDto> execute(int page, int itemsPerPage) {
    var commentsCollection = repository.findMany(page, itemsPerPage);
    return commentsCollection.map((comment) -> comment.getDto()).items();
  }
}
