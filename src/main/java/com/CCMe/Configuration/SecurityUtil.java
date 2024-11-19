package com.CCMe.Configuration;

import org.springframework.security.core.context.SecurityContextHolder;

import com.CCMe.Exception.ApiException;
import com.CCMe.Model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityUtil {
    // private static final HttpSessionSecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
    public static User getAuthenticated() throws Exception {
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("principal "+ principal.toString());
        if(principal instanceof User user) {
            return user;
        } else {
            log.error("User requested but not found in context");
            throw ApiException.builder().status(401).message("No auth").build();
        }
    }
}
