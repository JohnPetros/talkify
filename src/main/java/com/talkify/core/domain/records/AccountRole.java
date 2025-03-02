package com.talkify.core.domain.records;

import com.talkify.core.domain.exceptions.ValidationException;

public record AccountRole(String value) {
  public enum Role {
    ADMIN,
    ORGANIZATION
  }

  public static AccountRole create(String role) {
    if (role == null) {
      return new AccountRole(Role.ORGANIZATION.toString());
    }

    var text = Text.create(role.toUpperCase(), "account role");

    if (text.notEqualsTo(Role.ADMIN.toString()) && text.notEqualsTo(Role.ORGANIZATION.toString())) {
      throw new ValidationException("account role", "must be admin or organization");
    }

    return new AccountRole(text.value());
  }

  public Boolean isAdmin() {
    return value == Role.ADMIN.toString();
  }

  public Boolean isOrganization() {
    return value == Role.ORGANIZATION.toString();
  }
}
