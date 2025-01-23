package com.CCMe.Service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.CCMe.Configuration.SecurityUtil;
import com.CCMe.Model.Skill;
import com.CCMe.Model.User;
import com.CCMe.Repository.UserRepository;

import lombok.AllArgsConstructor;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@AllArgsConstructor
public class S3Service {
    
    private static final String BUCKET_NAME = "ccme";
    private final S3Client s3Client;
    private final UserRepository userRepository;

    public String uploadFile(MultipartFile file, Long id) throws Exception {
        User user = SecurityUtil.getAuthenticated();
        String keyName = Long.toString(user.getId())+"/license/"+Long.toString(id)+'/'+file.getOriginalFilename();
        try {
            s3Client.putObject(PutObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(keyName)
                .acl(ObjectCannedACL.PUBLIC_READ)
                .build(), RequestBody.fromInputStream(file.getInputStream(), file.getSize())
            );
        } catch (AwsServiceException | SdkClientException | IOException e) {
            e.printStackTrace();
        }
        GetUrlRequest getUrlRequest = GetUrlRequest.builder()
            .bucket(BUCKET_NAME)
            .key(keyName)
            .build();
        try {
            String res = s3Client.utilities().getUrl(getUrlRequest).toURI().toString();
            System.out.println("Setting License Picture: " + res);
            return res;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return "not found";
        }
    }
    
    // Method for Profile Picure
    public String uploadFile(Long id, MultipartFile file) {
        String keyName = Long.toString(id)+"/"+file.getOriginalFilename(); 
        try {
            s3Client.putObject(PutObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(keyName)
                .acl(ObjectCannedACL.PUBLIC_READ)
                .build(), RequestBody.fromInputStream(file.getInputStream(), file.getSize())
            );
        } catch (AwsServiceException | SdkClientException | IOException e) {
            e.printStackTrace();
        }
        GetUrlRequest getUrlRequest = GetUrlRequest.builder()
            .bucket(BUCKET_NAME)
            .key(keyName)
            .build();
        try {
            User user = userRepository.findById(id).get();
            String res = s3Client.utilities().getUrl(getUrlRequest).toURI().toString();
            System.out.println("Setting Profile Picture: " + res);
            user.setProfilePictureUrl(res);
            userRepository.save(user);
            return res;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return "not found";
        }
    }

    public String getFile(Long id) {
        String keyName = Long.toString(id)+"/pp"; 
        try {
            GetUrlRequest getUrlRequest = GetUrlRequest.builder().bucket(BUCKET_NAME).key(keyName).build();
            return s3Client.utilities().getUrl(getUrlRequest).toURI().toString();
        } catch(Exception e) {
            throw new RuntimeException("Failed to get URL of uploaded file", e);
        }
    }
}
