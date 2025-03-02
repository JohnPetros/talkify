package com.talkify.server.database.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.talkify.core.domain.entities.Account;
import com.talkify.core.domain.records.Id;
import com.talkify.core.domain.records.Text;
import com.talkify.core.interfaces.repositories.AccountsRepository;
import com.talkify.server.database.mappers.JpaAccountMapper;
import com.talkify.server.database.models.AccountModel;

import java.util.Optional;
import java.util.UUID;

interface JpaAccountModelRepository extends JpaRepository<AccountModel, UUID> {
  public Optional<AccountModel> findByName(String name);

  public Optional<AccountModel> findByEmail(String email);
}

public class JpaAccountsRepository implements AccountsRepository {
  @Autowired
  private JpaAccountModelRepository accountsRepository;

  @Autowired
  JpaAccountMapper accountMapper;

  @Override
  public Optional<Account> findById(Id accountId) {
    var accountModel = accountsRepository.findById(accountId.value());
    if (accountModel.isEmpty()) {
      return Optional.empty();
    }

    var account = accountMapper.toEntity(accountModel.get());
    return Optional.of(account);
  }

  @Override
  public Optional<Account> findByName(Text accountName) {
    var accountModel = accountsRepository.findByName(accountName.value());
    if (accountModel.isEmpty()) {
      return Optional.empty();
    }

    var account = accountMapper.toEntity(accountModel.get());
    return Optional.of(account);
  }

  @Override
  public Optional<Account> findByEmail(Text accountEmail) {
    var accountModel = accountsRepository.findByEmail(accountEmail.value());
    if (accountModel.isEmpty()) {
      return Optional.empty();
    }

    var account = accountMapper.toEntity(accountModel.get());
    return Optional.of(account);
  }

  @Override
  public void add(Account account) {
    var accountModel = accountMapper.toModel(account);
    accountsRepository.save(accountModel);
  }

}
