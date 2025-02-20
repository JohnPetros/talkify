package com.talkify.core.use_cases;

import com.talkify.core.domain.dtos.CommentDto;
import com.talkify.core.domain.entities.Comment;
import com.talkify.core.domain.entities.Talker;
import com.talkify.core.domain.exceptions.NotFoundException;
import com.talkify.core.interfaces.repositories.CommentsRepository;
import com.talkify.core.interfaces.repositories.TalkersRepository;

public class PostCommentUseCase {
  CommentsRepository commentsRepository;
  TalkersRepository talkersRepository;

  public PostCommentUseCase(CommentsRepository commentsRepository, TalkersRepository talkersRepository) {
    this.commentsRepository = commentsRepository;
    this.talkersRepository = talkersRepository;
  }

  public void execute(CommentDto commentDto, String talkerId, String documentId) {
    var currentTalker = talkersRepository.findById(talkerId);
    Talker commentTalker;
    System.out.println(currentTalker.isEmpty());
    if (currentTalker.isEmpty()) {
      commentTalker = new Talker(commentDto.talker);
      // talkersRepository.add(commentTalker);
    } else {
      commentTalker = currentTalker.get();
    }

    commentDto.setTalker(commentTalker.getDto());
    var comment = new Comment(commentDto);

    System.out.println(comment);
  }
}
