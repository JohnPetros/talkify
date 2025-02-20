package com.talkify.server.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.talkify.core.interfaces.repositories.CommentsRepository;
import com.talkify.core.interfaces.repositories.TalkersRepository;
import com.talkify.server.database.repositories.JpaCommentsRepository;
import com.talkify.server.database.repositories.JpaTalkersRepository;

@Configuration
public class DatabaseConfiguration {
  @Bean
  CommentsRepository commentsRepository() {
    return new JpaCommentsRepository();
  }

  @Bean
  TalkersRepository talkersRepository() {
    return new JpaTalkersRepository();
  }
}
