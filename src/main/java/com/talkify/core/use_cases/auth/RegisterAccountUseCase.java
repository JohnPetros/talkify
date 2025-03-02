package com.talkify.core.use_cases.auth;

import com.talkify.core.domain.dtos.AccountDto;
import com.talkify.core.domain.entities.Account;
import com.talkify.core.domain.exceptions.ExistingEmailException;
import com.talkify.core.domain.records.Text;
import com.talkify.core.interfaces.providers.AuthenticationProvider;
import com.talkify.core.interfaces.repositories.AccountsRepository;

public class RegisterAccountUseCase {
  private AccountsRepository repository;

  private AuthenticationProvider authenticationProvider;

  public RegisterAccountUseCase(AccountsRepository repository, AuthenticationProvider authenticationProvider) {
    this.repository = repository;
    this.authenticationProvider = authenticationProvider;
  }

  public AccountDto execute(String name, String email, String password) {
    var accountWithExistingEmail = repository.findByEmail(Text.create(email, "Account email"));

    if (accountWithExistingEmail.isPresent()) {
      throw new ExistingEmailException(email);
    }

    var accountDto = authenticationProvider.register(name, email, password);
    var account = new Account(accountDto);
    repository.add(account);
    return account.getDto(false);
  }
}
