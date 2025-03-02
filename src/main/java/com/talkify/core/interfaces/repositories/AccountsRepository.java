package com.talkify.core.interfaces.repositories;

import java.util.Optional;

import com.talkify.core.domain.entities.Account;
import com.talkify.core.domain.records.Id;
import com.talkify.core.domain.records.Text;

public interface AccountsRepository {
  Optional<Account> findById(Id accountId);

  Optional<Account> findByName(Text accountName);

  Optional<Account> findByEmail(Text accountEmail);

  void add(Account account);
}
