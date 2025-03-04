package com.talkify.server.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.talkify.core.interfaces.repositories.AccountsRepository;
import com.talkify.core.interfaces.repositories.CommentsRepository;
import com.talkify.server.database.repositories.JpaAccountsRepository;
import com.talkify.server.database.repositories.JpaCommentsRepository;

@Configuration
public class DatabaseConfiguration {
  @Bean
  CommentsRepository commentsRepository() {
    return new JpaCommentsRepository();
  }

  @Bean
  AccountsRepository accountsRepository() {
    return new JpaAccountsRepository();
  }
}
