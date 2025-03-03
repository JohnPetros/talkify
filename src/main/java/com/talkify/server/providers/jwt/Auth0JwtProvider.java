package com.talkify.server.providers.jwt;

import java.time.ZoneOffset;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.talkify.core.domain.records.DateTime;
import com.talkify.core.interfaces.providers.EnvProvider;
import com.talkify.core.interfaces.providers.JwtProvider;

public class Auth0JwtProvider implements JwtProvider {
  private final String secret;
  private final String issuer;
  private final Algorithm algorithm;

  public Auth0JwtProvider(EnvProvider envProvider) {
    var jwtSecret = envProvider.get("JWT_SECRET");

    if (jwtSecret == null) {
      throw new IllegalStateException("Missing required environment variable for JWT Secret.");
    }

    this.secret = jwtSecret;
    this.issuer = "talkify-server";
    this.algorithm = Algorithm.HMAC256(secret);
  }

  @Override
  public String generateToken(String email) {
    var expirationDate = DateTime.createFromNow().addHours(1);

    var jwt = JWT
        .create()
        .withIssuer(issuer)
        .withSubject(email)
        .withExpiresAt(expirationDate.value().toInstant(ZoneOffset.of("-03:00")))
        .sign(algorithm);

    return jwt;
  }

  @Override
  public String validateToken(String token) {
    var subject = JWT.require(algorithm).withIssuer(issuer).build().verify(token).getSubject();
    return subject;
  }

}
