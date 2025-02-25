package com.talkify.core.use_cases.comments;

import com.talkify.core.domain.dtos.CommentDto;
import com.talkify.core.domain.dtos.TalkerDto;
import com.talkify.core.domain.entities.Comment;
import com.talkify.core.domain.entities.Talker;
import com.talkify.core.domain.exceptions.ConflictException;
import com.talkify.core.interfaces.repositories.CommentsRepository;
import com.talkify.core.interfaces.repositories.TalkersRepository;

public class PostCommentUseCase {
  CommentsRepository commentsRepository;
  TalkersRepository talkersRepository;

  public PostCommentUseCase(CommentsRepository commentsRepository, TalkersRepository talkersRepository) {
    this.commentsRepository = commentsRepository;
    this.talkersRepository = talkersRepository;
  }

  public CommentDto execute(CommentDto commentDto, String documentId) {
    Talker commentTalker;

    if (commentDto.talker.id == null) {
      commentTalker = addTalkerToRepository(commentDto.talker);
    } else {
      var currentTalker = talkersRepository.findById(commentDto.talker.id);
      commentTalker = currentTalker.get();
    }

    commentDto.setTalker(commentTalker.getDto());
    var comment = new Comment(commentDto);
    return comment.getDto();
  }

  private Talker addTalkerToRepository(TalkerDto dto) {
    var talker = new Talker(dto);
    var talkerEmail = talker.getEmail().value();
    var talkerWithExistingEmail = talkersRepository.findByEmail(talkerEmail);

    if (talkerWithExistingEmail.isPresent())
      throw new ConflictException("E-mail já em uso");
    talkersRepository.add(talker);

    return talker;
  }
}
