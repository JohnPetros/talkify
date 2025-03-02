package com.talkify.core.interfaces.providers;

public interface StorageProvider {
  String uploadFile(String fileName, String fileContentType, byte[] fileBytes);

  String getFileUrl(String fileKey);
}
