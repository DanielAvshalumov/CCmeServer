package com.CCMe.Controller;

import javax.naming.AuthenticationException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CCMe.Model.Request.LogInRequest;
import com.CCMe.Service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin(origins="*", maxAge=3600)
public class AuthController {

    private final AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest req, HttpServletResponse res, @Valid @RequestBody LogInRequest body) throws AuthenticationException {
        authService.login(req,res,body);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/me")
    public ResponseEntity<?> getSesstion(HttpServletRequest req) throws Exception {
        authService.getSession(req);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest req, HttpServletResponse res) {
        authService.logout(req,res);
        return ResponseEntity.ok().build();
    }

}
