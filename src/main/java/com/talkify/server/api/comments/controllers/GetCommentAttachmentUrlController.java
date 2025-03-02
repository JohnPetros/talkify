package com.talkify.server.api.comments.controllers;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.talkify.core.domain.exceptions.AppException;
import com.talkify.core.interfaces.providers.StorageProvider;
import com.talkify.core.interfaces.repositories.CommentsRepository;
import com.talkify.core.use_cases.comments.GetCommentAttachmentUseCase;

import lombok.Data;

@CommentsController
public class GetCommentAttachmentUrlController {

  @Autowired
  private CommentsRepository commentsRepository;

  @Autowired
  private StorageProvider storageProvider;

  @Data
  private static class Response {
    private String url;
  }

  @GetMapping("attachments/{attachmentKey}")
  public ResponseEntity<Resource> handle(@PathVariable String attachmentKey) {
    var useCase = new GetCommentAttachmentUseCase(commentsRepository);
    var attachment = useCase.execute(attachmentKey);

    try {
      var fileUri = new URI(storageProvider.getFileUrl(attachmentKey));
      var resource = new UrlResource(fileUri);

      return ResponseEntity.ok()
          .header(HttpHeaders.CONTENT_DISPOSITION,
              String.format("attachment; filename=\"%s\"", attachment.name))
          .contentType(MediaType.valueOf(attachment.contentType))
          .body(resource);
    } catch (URISyntaxException | MalformedURLException | RuntimeException exception) {
      throw new AppException(exception.getMessage());
    }
  }
}
