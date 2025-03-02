package com.talkify.core.use_cases.auth;

import com.talkify.core.domain.dtos.AccountDto;
import com.talkify.core.domain.exceptions.AccountNotFoundExeception;
import com.talkify.core.domain.records.Text;
import com.talkify.core.interfaces.repositories.AccountsRepository;

public class GetAccountUseCase {
  private AccountsRepository repository;

  public GetAccountUseCase(AccountsRepository repository) {
    this.repository = repository;
  }

  public AccountDto execute(String email) {
    var account = repository.findByEmail(Text.create(email, "Account email"));
    if (account.isEmpty()) {
      throw new AccountNotFoundExeception();
    }
    return account.get().getDto(true);
  }
}
