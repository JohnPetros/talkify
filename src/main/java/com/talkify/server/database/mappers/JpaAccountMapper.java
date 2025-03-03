package com.talkify.server.database.mappers;

import org.springframework.stereotype.Service;

import com.talkify.core.domain.dtos.AccountDto;
import com.talkify.core.domain.entities.Account;
import com.talkify.server.database.models.AccountModel;

@Service
public class JpaAccountMapper {
  public Account toEntity(AccountModel model) {
    var dto = new AccountDto()
        .setId(model.getId().toString())
        .setName(model.getName())
        .setEmail(model.getEmail())
        .setPassword(model.getPassword())
        .setRole(model.getRole().toString());

    return new Account(dto);
  }

  public AccountModel toModel(Account account) {
    var model = AccountModel
        .builder()
        .id(account.getId().value())
        .name(account.getName().value())
        .email(account.getEmail().value())
        .password(account.getPassword().value())
        .role(account.getRole().value())
        .build();

    return model;
  }
}
