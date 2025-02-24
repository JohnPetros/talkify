package com.talkify.server.database.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.talkify.core.domain.entities.Talker;
import com.talkify.core.interfaces.repositories.TalkersRepository;
import com.talkify.server.database.mappers.JpaTalkerMapper;
import com.talkify.server.database.models.TalkerModel;

import java.util.Optional;
import java.util.UUID;

interface Repository extends JpaRepository<TalkerModel, UUID> {
  TalkerModel findByEmail(String email);
}

public class JpaTalkersRepository implements TalkersRepository {
  @Autowired
  private Repository repository;

  @Autowired
  private JpaTalkerMapper mapper;

  @Override
  public Optional<Talker> findById(String talkerId) {
    var talkerModel = repository.findById(UUID.fromString(talkerId));

    if (talkerModel.isEmpty())
      return Optional.empty();

    var talker = mapper.toEntity(talkerModel.get());
    return Optional.of(talker);
  }

  @Override
  public void add(Talker talker) {
    var model = mapper.toModel(talker);
    repository.save(model);
    System.out.println(model);
  }

  @Override
  public Optional<Talker> findByEmail(String talkerEmail) {
    var talkerModel = repository.findByEmail(talkerEmail);

    System.out.println(talkerModel);

    if (talkerModel == null)
      return Optional.empty();

    var talker = mapper.toEntity(talkerModel);
    return Optional.of(talker);
  }
}
