package com.talkify.server.api.comments.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.talkify.core.domain.exceptions.AppException;
import com.talkify.core.interfaces.providers.StorageProvider;
import com.talkify.core.interfaces.repositories.CommentsRepository;
import com.talkify.core.use_cases.comments.UploadCommentAttachmentUseCase;

import lombok.AllArgsConstructor;
import lombok.Data;

@CommentsController
public class UploadCommentAttachementController {
  @Autowired
  private CommentsRepository commentsRepository;

  @Autowired
  private StorageProvider storageProvider;

  @Data
  @AllArgsConstructor
  private static class Response {
    private String attachmentUrl;
  }

  @PostMapping(value = "/attachments/upload", consumes = "multipart/form-data")
  public ResponseEntity<Response> handle(
      @RequestParam MultipartFile attachment,
      @RequestParam(name = "comment_id") String commentId) {
    var useCase = new UploadCommentAttachmentUseCase(commentsRepository, storageProvider);

    try {
      var attachmentKey = useCase.execute(
          commentId,
          attachment.getOriginalFilename(),
          attachment.getContentType(),
          attachment.getBytes());

      var baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
      var attachmentUrl = baseUrl + "/comments/attachments/" + attachmentKey;
      var response = new Response(attachmentUrl);
      return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } catch (Exception exception) {
      throw new AppException(exception.getMessage());
    }
  }

}
