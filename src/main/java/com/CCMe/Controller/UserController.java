package com.CCMe.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.CCMe.Configuration.ApplicationProperties;
import com.CCMe.Model.Request.CreateUserRequest;
import com.CCMe.Model.Request.ForgotPasswordRequest;
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

    @PostMapping("/forgot-password")
    public ResponseEntity<Void> forgotPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        userService.forgotPassword(forgotPasswordRequest.getEmail());
        return ResponseEntity.ok().build();
    }

}
