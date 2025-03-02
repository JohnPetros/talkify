package com.talkify.core.interfaces.providers;

import com.talkify.core.domain.dtos.AccountDto;

public interface AuthenticationProvider {
  String login(String email, String password);

  AccountDto register(String name, String email, String password);

  void logout();
}