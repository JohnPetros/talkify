package com.talkify.server.database.mappers;

import org.springframework.stereotype.Service;

import com.talkify.core.domain.records.Id;
import com.talkify.server.database.models.TalkerModel;

@Service
public class JpaTalkerMapper {

  public TalkerModel toModel(Id talkerId) {
    var model = TalkerModel.builder()
        .id(talkerId.value())
        .build();

    return model;
  }

}
