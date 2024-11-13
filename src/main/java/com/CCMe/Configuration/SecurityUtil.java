package com.CCMe.Configuration;

import org.springframework.security.core.context.SecurityContextHolder;

import com.CCMe.Model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityUtil {
    // private static final HttpSessionSecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
    public static User getAuthenticated() throws Exception {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof User user) {
            return user;
        } else {
            log.error("User requested but not found in context");
            throw new Exception();
        }
    }
}
