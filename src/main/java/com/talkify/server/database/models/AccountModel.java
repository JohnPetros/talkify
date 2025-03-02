package com.talkify.server.database.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.talkify.core.domain.records.AccountRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "accounts")
public class AccountModel {
  @Id
  private UUID id;

  @Column
  private String name;

  @Column
  private String email;

  @Column
  private String password;

  @Column
  private AccountRole.Role role;

  @OneToMany(mappedBy = "organization")
  @Builder.Default
  private List<TalkerModel> talkers = new ArrayList<>();;

}
