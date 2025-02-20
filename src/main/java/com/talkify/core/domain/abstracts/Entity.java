package com.talkify.core.domain.abstracts;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.UUID;

public abstract class Entity {
  private final UUID id;

  protected Entity(String id) {
    this.id = (id != null) ? UUID.fromString(id) : UUID.randomUUID();
  }

  public UUID getId() {
    return id;
  }

  public Boolean isEqualTo(Entity entity) {
    return id == entity.id;
  }

  @Override
  public boolean equals(Object object) {
    Entity entity = (Entity) object;
    return Objects.equals(getId(), entity.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    Field[] fields = this.getClass().getDeclaredFields();
    StringBuilder fieldsString = new StringBuilder("{ ");

    for (Field field : fields) {
      field.setAccessible(true);
      try {
        fieldsString.append(field.getName()).append(": ").append(field.get(this)).append(", ");
      } catch (IllegalAccessException e) {
        fieldsString.append(field.getName()).append(": <inaccessible>, ");
      }
    }

    if (fields.length > 0) {
      fieldsString.setLength(fieldsString.length() - 2);
    }

    return fieldsString.append(" }").toString();
  }
}
