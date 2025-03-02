package com.talkify.core.domain.exceptions;

public class AccountNotFoundExeception extends RuntimeException {
  public AccountNotFoundExeception() {
    super("Account not found");
  }

}
