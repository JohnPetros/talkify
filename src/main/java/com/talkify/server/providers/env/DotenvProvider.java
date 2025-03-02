package com.talkify.server.providers.env;

import com.talkify.core.interfaces.providers.EnvProvider;

import io.github.cdimascio.dotenv.Dotenv;

public class DotenvProvider implements EnvProvider {
  private final Dotenv dotenv;

  public DotenvProvider() {
    dotenv = Dotenv.load();
  }

  @Override
  public String get(String key) {
    return dotenv.get(key);
  }

}
