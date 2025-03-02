package com.talkify.server.providers.storage;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.S3Configuration;

import java.nio.ByteBuffer;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;

import com.talkify.core.domain.exceptions.AppException;
import com.talkify.core.interfaces.providers.EnvProvider;
import com.talkify.core.interfaces.providers.StorageProvider;

public class S3StorageProvider implements StorageProvider {
  private final S3Client s3Client;

  @Value("${storage.bucket.name}")
  private String bucketName;

  public static class S3Config {
    private final String accessKey;
    private final String secretKey;
    private final String region;

    public S3Config(String accessKey, String secretKey, String region) {
      this.accessKey = accessKey;
      this.secretKey = secretKey;
      this.region = region;
    }

    public String getAccessKey() {
      return accessKey;
    }

    public String getSecretKey() {
      return secretKey;
    }

    public String getRegion() {
      return region;
    }
  }

  public S3StorageProvider(EnvProvider envProvider) {
    var accessKey = envProvider.get("S3_ACCESS_KEY");
    var secretKey = envProvider.get("S3_SECRET_ACCESS_KEY");
    var region = envProvider.get("S3_REGION");

    if (accessKey == null || secretKey == null || region == null) {
      throw new IllegalStateException("Missing required environment variables for R2 Storage.");
    }

    var config = new S3Config(accessKey, secretKey, region);
    this.s3Client = buildS3Client(config);
  }

  private static S3Client buildS3Client(S3Config config) {
    var credentials = AwsBasicCredentials.create(config.getAccessKey(), config.getSecretKey());

    var serviceConfiguration = S3Configuration
        .builder()
        .pathStyleAccessEnabled(true)
        .build();

    return S3Client
        .builder()
        .credentialsProvider(StaticCredentialsProvider.create(credentials))
        .region(Region.of(config.getRegion()))
        .serviceConfiguration(serviceConfiguration)
        .build();
  }

  @Override
  public String uploadFile(String fileName, String fileContentType, byte[] fileBytes) {
    var fileKey = UUID.randomUUID() + "-" + fileName;

    try {
      var objectRequest = PutObjectRequest.builder()
          .bucket(bucketName)
          .key("organization/" + fileKey)
          .contentType(fileContentType)
          .build();
      var requestBody = RequestBody.fromByteBuffer(ByteBuffer.wrap(fileBytes));

      s3Client.putObject(objectRequest, requestBody);

      return fileKey;
    } catch (Exception exception) {
      System.out.println(exception);
      throw new AppException("Storage exception", "failed to upload file");
    }
  }

  public String getFileUrl(String fileKey) {
    GetUrlRequest urlRequest = GetUrlRequest.builder()
        .bucket(bucketName)
        .key("organization/" + fileKey)
        .build();
    return s3Client.utilities().getUrl(urlRequest).toString();
  }

}
