package com.talkify.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.talkify.core.domain.entities.Account;
import com.talkify.core.domain.exceptions.NotAuthenticatedException;
import com.talkify.core.interfaces.repositories.AccountsRepository;
import com.talkify.core.use_cases.auth.GetAccountUseCase;

@Service
public class SecurityUserService implements UserDetailsService {

  @Autowired
  private AccountsRepository accountsRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    try {
      var useCase = new GetAccountUseCase(accountsRepository);
      var accountDto = useCase.execute(email);
      var account = new Account(accountDto);
      var securityUser = new SecurityUser(account);
      return securityUser;
    } catch (Exception e) {
      throw new NotAuthenticatedException("Account not found");
    }
  }
}
