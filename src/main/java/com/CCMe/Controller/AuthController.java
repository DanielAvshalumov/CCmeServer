package com.CCMe.Controller;

import javax.naming.AuthenticationException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
public class AuthController {

    private final AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest req, HttpServletResponse res, @Valid @RequestBody LogInRequest body) throws AuthenticationException {
        authService.login(req,res,body);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<?> getSesstion(HttpServletRequest req) throws Exception {
        System.out.println(req.getCookies());
        return ResponseEntity.ok(authService.getSession(req));
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest req, HttpServletResponse res) {
        authService.logout(req,res);
        System.out.println(req.getCookies());
        return ResponseEntity.ok().build();
    }

}
