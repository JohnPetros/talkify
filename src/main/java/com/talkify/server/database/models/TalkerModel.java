package com.talkify.server.database.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "talkers")
public class TalkerModel {
  @Id
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "organization_id")
  private AccountModel organization;

  @OneToMany(mappedBy = "talker", fetch = FetchType.LAZY)
  private List<CommentModel> votedComments;

}
