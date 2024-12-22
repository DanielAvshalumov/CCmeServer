package com.CCMe.Controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.CCMe.Service.S3Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/s3")
public class S3Controller {
    
    private final S3Service s3Service;

    @RequestMapping(value="/upload", method=RequestMethod.PATCH, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file, @RequestParam("id") Long Id) {
        return ResponseEntity.ok(s3Service.uploadFile(Id, file));
    }

    @GetMapping("/get-profile-picture")
    public ResponseEntity<String> get(@RequestParam("id") Long Id) {
        return ResponseEntity.ok(s3Service.getFile(Id));
    }
}
