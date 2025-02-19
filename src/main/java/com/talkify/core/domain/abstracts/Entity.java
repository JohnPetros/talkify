package com.talkify.core.domain.abstracts;

import java.util.UUID;

public abstract class Entity {
  private final String id;

  protected Entity(String id) {
    this.id = (id != null) ? id : UUID.randomUUID().toString();
  }

  public String getId() {
    return id;
  }

  public Boolean isEqualTo(Entity entity) {
    return id == entity.id;
  }
}
