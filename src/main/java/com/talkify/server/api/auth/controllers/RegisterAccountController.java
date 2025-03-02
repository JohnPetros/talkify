package com.talkify.server.api.auth.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.talkify.core.domain.dtos.AccountDto;
import com.talkify.core.interfaces.providers.AuthenticationProvider;
import com.talkify.core.interfaces.repositories.AccountsRepository;
import com.talkify.core.use_cases.auth.RegisterAccountUseCase;

@AuthController
public class RegisterAccountController {
  @Autowired
  private AccountsRepository repository;

  @Autowired
  private AuthenticationProvider authenticationProvider;

  @Data
  private static class Request {
    private String name;
    private String email;
    private String password;
  }

  @Data
  @AllArgsConstructor
  private static class Response {
    private String jwt;
  }

  @PostMapping("/register")
  public ResponseEntity<AccountDto> handle(@RequestBody Request body) {
    var useCase = new RegisterAccountUseCase(repository, authenticationProvider);
    var accountDto = useCase.execute(body.getName(), body.getEmail(), body.getPassword());
    return ResponseEntity.status(HttpStatus.CREATED).body(accountDto);
  }
}
