package com.talkify.core.domain.entities;

import com.talkify.core.domain.abstracts.Entity;
import com.talkify.core.domain.dtos.TalkerDto;
import com.talkify.core.domain.records.Email;
import com.talkify.core.domain.records.Text;

public final class Talker extends Entity {
  private Text name;
  private Email email;

  public Talker(TalkerDto dto) {
    super(dto.id);
    this.name = Text.create(dto.name, "Talker name");
    this.email = Email.create(dto.email, "Talker email");
  }

  public Text getName() {
    return name;
  }

  public Email getEmail() {
    return email;
  }

  public TalkerDto getDto() {
    return new TalkerDto()
        .setId(this.getId().toString())
        .setName(this.getName().value())
        .setEmail(this.getEmail().value());
  }
}
