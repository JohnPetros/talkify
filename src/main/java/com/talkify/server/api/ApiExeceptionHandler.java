package com.talkify.server.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.talkify.core.domain.exceptions.AppException;
import com.talkify.core.domain.exceptions.NotFoundException;
import com.talkify.core.domain.exceptions.ValidationException;

@ControllerAdvice
public class ApiExeceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler
  private ResponseEntity<AppException> notFoundExceptionHandler(NotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
  }

  @ExceptionHandler
  private ResponseEntity<AppException> validationExceptionHandler(ValidationException exception) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
  }
}
