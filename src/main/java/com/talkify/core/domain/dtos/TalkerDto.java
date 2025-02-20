package com.talkify.core.domain.dtos;

public class TalkerDto {
  public String id;
  public String name;
  public String email;

  public TalkerDto setId(String id) {
    this.id = id;
    return this;
  }

  public TalkerDto setName(String name) {
    this.name = name;
    return this;
  }

  public TalkerDto setEmail(String email) {
    this.email = email;
    return this;
  }
}
