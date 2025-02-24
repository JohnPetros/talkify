package com.talkify.core.interfaces.repositories;

import java.util.Optional;

import com.talkify.core.domain.entities.Talker;

public interface TalkersRepository {
  void add(Talker talker);

  Optional<Talker> findById(String talkerId);

  Optional<Talker> findByEmail(String talkerEmail);
}
