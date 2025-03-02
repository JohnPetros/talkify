package com.talkify.server.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.talkify.core.domain.entities.Account;
import com.talkify.core.interfaces.providers.JwtProvider;
import com.talkify.core.interfaces.repositories.AccountsRepository;
import com.talkify.core.use_cases.auth.GetAccountUseCase;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityJwtFilter extends OncePerRequestFilter {
  @Autowired
  AccountsRepository accountsRepository;

  @Autowired
  JwtProvider jwtProvider;

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    var jwt = recoverJwt(request);

    if (jwt != null) {
      var subject = jwtProvider.validateToken(jwt);
      var account = getAccount(subject);
      var securityUser = new SecurityUser(account);
      var authentication = new UsernamePasswordAuthenticationToken(
          securityUser,
          null,
          securityUser.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);
  }

  private String recoverJwt(HttpServletRequest request) {
    var authorizationHeader = request.getHeader("Authorization");
    if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
      return null;
    }
    return authorizationHeader.replace("Bearer ", "");
  }

  private Account getAccount(String email) {
    var useCase = new GetAccountUseCase(accountsRepository);
    var accountDto = useCase.execute(email);
    return new Account(accountDto);
  }
}
