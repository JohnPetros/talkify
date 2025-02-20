package com.talkify.server.database.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.talkify.core.domain.entities.Talker;
import com.talkify.core.interfaces.repositories.TalkersRepository;
import com.talkify.server.database.mappers.JpaTalkerMapper;
import com.talkify.server.database.models.TalkerModel;

import java.util.Optional;
import java.util.UUID;

interface Jpa extends JpaRepository<TalkerModel, UUID> {
}

public class JpaTalkersRepository implements TalkersRepository {
  @Autowired
  Jpa jpa;

  @Autowired
  JpaTalkerMapper mapper;

  @Override
  public Optional<Talker> findById(String talkerId) {
    var talkerModel = jpa.findById(UUID.fromString(talkerId));

    if (talkerModel.isEmpty())
      return Optional.empty();

    var talker = mapper.toEntity(talkerModel.get());
    return Optional.of(talker);
  }

  @Override
  public void add(Talker talker) {
    var model = mapper.toModel(talker);
    jpa.save(model);
  }
}
