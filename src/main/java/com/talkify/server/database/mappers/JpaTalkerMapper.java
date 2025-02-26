package com.talkify.server.database.mappers;

import org.springframework.stereotype.Service;

import com.talkify.core.domain.dtos.TalkerDto;
import com.talkify.core.domain.entities.Talker;
import com.talkify.server.database.models.TalkerModel;

@Service
public class JpaTalkerMapper {
  public Talker toEntity(TalkerModel model) {
    var dto = new TalkerDto().setId(model.getId().toString());

    return new Talker(dto);
  }

  public TalkerModel toModel(Talker entity) {
    var model = TalkerModel.builder()
        .id(entity.getId())
        .build();

    return model;
  }

}
