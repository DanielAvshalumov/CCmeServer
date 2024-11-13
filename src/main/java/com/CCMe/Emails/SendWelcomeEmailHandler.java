package com.CCMe.Emails;

import java.util.List;

import org.jobrunr.jobs.lambdas.JobRequestHandler;
import org.springframework.stereotype.Component;

import com.CCMe.Configuration.ApplicationProperties;
import com.CCMe.Model.User;
import com.CCMe.Model.VerificationCode;
import com.CCMe.Repository.UserRepository;
import com.CCMe.Repository.VerificationCodeRepository;
import com.CCMe.Service.EmailService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Component
@Slf4j
@RequiredArgsConstructor
public class SendWelcomeEmailHandler implements JobRequestHandler<SendWelcomeEmail>{
     
    private final UserRepository userRepository;
    private final VerificationCodeRepository verificationCodeRepository;
    private final SpringTemplateEngine templateEngine;
    private final EmailService emailService;
    private final ApplicationProperties applicationProperties;

    @Override
    @Transactional
    public void run(SendWelcomeEmail jobRequest) {
        User user = userRepository.findById(jobRequest.getUserId())
            .orElseThrow(() -> new RuntimeException());
        log.info("Sending welcome email to user with id: {}",jobRequest.getUserId());
        if(user.getVerificationCode() != null && !user.getVerificationCode().isEmailSent()) {
            sendWelcomeEmail(user, user.getVerificationCode());
        }
    }

    private void sendWelcomeEmail(User user, VerificationCode code) {
        String verificationLink = applicationProperties.getBaseUrl() + "/auth/verify-email?token=" + code.getCode();
        Context leafContext = new Context();
        leafContext.setVariable("user", user);
        leafContext.setVariable("verificationLink", verificationLink);
        leafContext.setVariable("applicationName", applicationProperties.getApplicationName());
        String htmlBody = templateEngine.process("welcome-email", leafContext);
        emailService.sendHtmlMessage(List.of(user.getEmail()), "Welcome to our platform", htmlBody);
        code.setEmailSent(true);
        verificationCodeRepository.save(code);
    }
    
}
