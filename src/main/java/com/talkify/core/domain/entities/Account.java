package com.talkify.core.domain.entities;

import com.talkify.core.domain.abstracts.Entity;
import com.talkify.core.domain.dtos.AccountDto;
import com.talkify.core.domain.records.AccountRole;
import com.talkify.core.domain.records.Email;
import com.talkify.core.domain.records.Text;

public final class Account extends Entity {
  private Text name;
  private Email email;
  private Text password;
  private AccountRole role;

  public Account(AccountDto dto) {
    super(dto.id);
    name = Text.create(dto.name, "Account name");
    email = Email.create(dto.email, "Account email");
    password = Text.create(dto.password, "Account password");
    role = AccountRole.create(dto.role);
  }

  public Text getName() {
    return name;
  }

  public Email getEmail() {
    return email;
  }

  public Text getPassword() {
    return password;
  }

  public AccountRole getRole() {
    return role;
  }

  public AccountDto getDto(boolean shouldIncludePassword) {
    var dto = new AccountDto()
        .setId(getId().value().toString())
        .setName(getName().value())
        .setEmail(getEmail().value())
        .setRole(getRole().toString());

    if (shouldIncludePassword) {
      dto.setPassword(getPassword().value());
    }

    return dto;
  }

}
