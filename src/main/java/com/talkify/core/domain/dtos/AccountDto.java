package com.talkify.core.domain.dtos;

public class AccountDto {
  public String id;
  public String name;
  public String email;
  public String password;
  public String role;

  public AccountDto setId(String id) {
    this.id = id;
    return this;
  }

  public AccountDto setName(String name) {
    this.name = name;
    return this;
  }

  public AccountDto setEmail(String email) {
    this.email = email;
    return this;
  }

  public AccountDto setPassword(String password) {
    this.password = password;
    return this;
  }

  public AccountDto setRole(String role) {
    this.role = role;
    return this;
  }
}
