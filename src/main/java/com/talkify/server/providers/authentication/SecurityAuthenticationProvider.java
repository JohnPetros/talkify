package com.talkify.server.providers.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.talkify.core.domain.dtos.AccountDto;
import com.talkify.core.domain.records.Id;
import com.talkify.core.interfaces.providers.AuthenticationProvider;
import com.talkify.core.interfaces.providers.JwtProvider;

public class SecurityAuthenticationProvider implements AuthenticationProvider {
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtProvider jwtProvider;

  @Override
  public String login(String email, String password) {
    var authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
    authenticationManager.authenticate(authenticationToken);
    var jwt = jwtProvider.generateToken(email);
    return jwt;
  }

  @Override
  public AccountDto register(String name, String email, String password) {
    var encryptedPassword = passwordEncoder.encode(password);

    var accountDto = new AccountDto()
        .setId(Id.random().toString())
        .setName(name)
        .setEmail(email)
        .setPassword(encryptedPassword);

    return accountDto;
  }

  @Override
  public void logout() {
    throw new UnsupportedOperationException("Unimplemented method 'logout'");
  }

}
