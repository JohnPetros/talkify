package com.talkify.core.interfaces.providers;

public interface JwtProvider {
  String generateToken(String subject);

  String validateToken(String token);
}
