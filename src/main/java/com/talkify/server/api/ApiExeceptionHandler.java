package com.talkify.server.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.talkify.core.domain.exceptions.AppException;
import com.talkify.core.domain.exceptions.ConflictException;
import com.talkify.core.domain.exceptions.NotFoundException;
import com.talkify.core.domain.exceptions.ValidationException;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class ExceptionMessage {
  private String title;
  private String message;
}

@ControllerAdvice
public class ApiExeceptionHandler {
  @ExceptionHandler(NotFoundException.class)
  private ResponseEntity<ExceptionMessage> handleNotFoundException(NotFoundException exception) {
    var message = new ExceptionMessage(exception.getTitle(), exception.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
  }

  @ExceptionHandler(ValidationException.class)
  private ResponseEntity<ExceptionMessage> handleValidationException(ValidationException exception) {
    var message = new ExceptionMessage(exception.getTitle(), exception.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
  }

  @ExceptionHandler(ConflictException.class)
  private ResponseEntity<ExceptionMessage> handleConflictException(ConflictException exception) {
    var message = new ExceptionMessage(exception.getTitle(), exception.getMessage());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
  }

  @ExceptionHandler(AppException.class)
  private ResponseEntity<ExceptionMessage> handleAppException(AppException exception) {
    var message = new ExceptionMessage(exception.getTitle(), exception.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
  }

  @ExceptionHandler(RuntimeException.class)
  private ResponseEntity<ExceptionMessage> handleRuntimeException(RuntimeException exception) {
    var message = new ExceptionMessage("Runtime exception", exception.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
  }

}
