package com.talkify.server.providers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.talkify.core.interfaces.providers.AuthenticationProvider;
import com.talkify.core.interfaces.providers.JwtProvider;
import com.talkify.core.interfaces.providers.StorageProvider;
import com.talkify.server.providers.authentication.SecurityAuthenticationProvider;
import com.talkify.server.providers.env.DotenvProvider;
import com.talkify.server.providers.jwt.Auth0JwtProvider;
import com.talkify.server.providers.storage.S3StorageProvider;

@Configuration
public class ProvidersConfiguration {
  @Bean
  JwtProvider jwtProvider() {
    return new Auth0JwtProvider(new DotenvProvider());
  }

  @Bean
  AuthenticationProvider authenticationProvider() {
    return new SecurityAuthenticationProvider();
  }

  @Bean
  StorageProvider storageProvider() {
    return new S3StorageProvider(new DotenvProvider());
  }
}
