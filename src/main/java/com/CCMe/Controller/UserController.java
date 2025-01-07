package com.CCMe.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.CCMe.Configuration.ApplicationProperties;
import com.CCMe.Model.Request.CreateUserRequest;
import com.CCMe.Model.Request.ForgotPasswordRequest;
import com.CCMe.Model.Request.UpdateUserRequest;
import com.CCMe.Model.Request.UserResponse;
import com.CCMe.Service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ApplicationProperties applicationProperties;

    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getAllNonContractors(@RequestParam(name="iscontractor") boolean isContractor) {
        List<UserResponse> res = userService.getAllNonContractors(isContractor);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") Long id) {
        UserResponse user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUserRequest req) {
        UserResponse user = userService.create(req);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/verify-user")
    public ResponseEntity<Void> verifyEmail(@RequestParam(name="code") String code) {
        userService.verifyEmail(code);
        return ResponseEntity.ok().build();
    }

    // @PostMapping("/forgot-password")
    // public ResponseEntity<Void> forgotPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) {
    //     userService.forgotPassword(forgotPasswordRequest.getEmail());
    //     return ResponseEntity.ok().build();
    // }

    @PutMapping("/update")
    public ResponseEntity<UserResponse> update(@RequestBody UpdateUserRequest updateUserRequest) throws Exception {
        userService.update(updateUserRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/description")
    public ResponseEntity<UserResponse> updateDescription(@RequestBody String description) throws Exception{
        UserResponse userResponse = userService.updateDescription(description);
        return ResponseEntity.ok(userResponse);
    }

    @PatchMapping("/profile-picture")
    public ResponseEntity<UserResponse> updateProfilePicture(@RequestParam("file") MultipartFile file) throws Exception{
        System.out.println("Original File Name: "+file.getOriginalFilename());
        UserResponse user = userService.updateProfilePicture(file);
        return ResponseEntity.ok(user);
    }

    // @PatchMapping("/profile-picture")
    // public String pp(@RequestParam("file") MultipartFile file) {
    //     try {
    //         if (file.isEmpty()) {
    //             return "No file uploaded";
    //         }
    //         // Process the file (e.g., save it, etc.)
    //         System.out.println("File uploaded: " + file.getOriginalFilename());
    //         return "File uploaded successfully";
    //     } catch (Exception e) {
    //         // Log and return a more detailed error message if necessary
    //         e.printStackTrace();
    //         return "Error uploading file: " + e.getMessage();
    //     }
    // }

}
