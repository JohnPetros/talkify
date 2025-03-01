package com.talkify.core.domain.exceptions;

public class TalkerNotFoundException extends NotFoundException {
  public TalkerNotFoundException(String talkerId) {
    super("Talker not found with the id equal to" + talkerId);
  }
}
