package com.talkify.server.api.auth.controllers;

import lombok.Data;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.talkify.core.interfaces.providers.AuthenticationProvider;

@AuthController
public class LoginAccouncController {
  @Autowired
  private AuthenticationProvider authenticationProvider;

  @Data
  private static class Request {
    private String email;
    private String password;
  }

  @Data
  @AllArgsConstructor
  private static class Response {
    private String jwt;
  }

  @PostMapping("/login")
  public ResponseEntity<Response> handle(@RequestBody Request body) {
    var jwt = authenticationProvider.login(body.getEmail(), body.getPassword());
    var response = new Response(jwt);
    return ResponseEntity.status(HttpStatus.OK).body(response);

  }
}
