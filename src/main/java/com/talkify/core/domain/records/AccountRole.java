package com.talkify.core.domain.records;

import com.talkify.core.domain.exceptions.ValidationException;

public record AccountRole(Role value) {
  public enum Role {
    ADMIN,
    ORGANIZATION
  }

  public static AccountRole create(String role) {
    if (role == null) {
      return new AccountRole(Role.ORGANIZATION);
    }

    var text = Text.create(role.toUpperCase(), "account role");

    if (text.notEqualsTo(Role.ADMIN.toString()) && text.notEqualsTo(Role.ORGANIZATION.toString())) {
      throw new ValidationException("account role", "must be admin or organization");
    }

    return new AccountRole(Role.valueOf(text.value()));
  }

  public String toString() {
    return value.toString().toLowerCase();
  }

  public Boolean isAdmin() {
    return value == Role.ADMIN;
  }

  public Boolean isOrganization() {
    return value == Role.ORGANIZATION;
  }
}
