package com.talkify.core.domain.exceptions;

public class AttachmentNotFoundException extends RuntimeException {
  public AttachmentNotFoundException() {
    super("Attachment not found");
  }
}
