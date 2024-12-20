package com.CCMe.Service;

import java.io.File;
import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@AllArgsConstructor
public class S3Service {
    
    private static final String BUCKET_NAME = "ccme";
    private final S3Client s3Client;

    public ResponseEntity<String> uploadFile(String keyName, MultipartFile file) {
        try {
            s3Client.putObject(PutObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(keyName)
                .build(), RequestBody.fromInputStream(file.getInputStream(), file.getSize())
            );
        } catch (AwsServiceException | SdkClientException | IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok((keyName + " has been uploaded"));
    }
}
