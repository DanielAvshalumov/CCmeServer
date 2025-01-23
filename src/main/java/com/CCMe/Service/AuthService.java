package com.CCMe.Service;


import javax.naming.AuthenticationException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.CCMe.Configuration.SecurityUtil;
import com.CCMe.Model.Skill;
import com.CCMe.Model.User;
import com.CCMe.Model.Request.LogInRequest;
import com.CCMe.Model.Request.UserResponse;
import com.CCMe.Repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    private SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    public void login(HttpServletRequest req, HttpServletResponse res, @Valid @RequestBody LogInRequest body) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
        SecurityContext context = securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(authentication);
        securityContextHolderStrategy.setContext(context);
        securityContextRepository.saveContext(context,req,res);
    }

    @Transactional
    public UserResponse getSession(HttpServletRequest req) throws Exception {
        User user = SecurityUtil.getAuthenticated();
        userRepository.getReferenceById(user.getId());
        for(Skill skill : user.getSkills()) {
            System.out.println(skill.getId());
        }
        return new UserResponse(user);
    }

    public void logout(HttpServletRequest req, HttpServletResponse res) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("principal "+auth.getPrincipal()+" creds "+auth.getCredentials());
        this.logoutHandler.setInvalidateHttpSession(true);
        this.logoutHandler.logout(req,res,auth);
    }
}
