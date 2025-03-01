package com.CCMe.Emails;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.jobrunr.jobs.lambdas.JobRequestHandler;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.CCMe.Configuration.ApplicationProperties;
import com.CCMe.Model.Job;
import com.CCMe.Model.Skill;
import com.CCMe.Model.User;
import com.CCMe.Repository.SkillRepository;
import com.CCMe.Repository.UserRepository;
import com.CCMe.Service.EmailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class SendJobAfterCreationEmailHandler implements JobRequestHandler<SendJobAfterCreationEmail> {
    
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
    private final EmailService emailService;
    private final TemplateEngine templateEngine;

    @Override
    public void run(SendJobAfterCreationEmail jobRequest) throws Exception {
        sendJobAfterCreationEmail(jobRequest.getEmails(), jobRequest.getJobId(), jobRequest.getMap(), jobRequest.getCompany(), jobRequest.getDescription());
    }
    
    private void sendJobAfterCreationEmail(List<String> emails, Long jobId, String map, String company, String description) {
        Context ctx = new Context();
        String applicationLink = "http://localhost:3000/dashboard/jobs/"+Long.toString(jobId);
        ctx.setVariable("company", company);
        ctx.setVariable("description", description);
        ctx.setVariable("applicationLink", applicationLink);
        ctx.setVariable("map", map);
        emails.add("avshalumov.daniel@gmail.com");
        String htmlBody = templateEngine.process("job-after-creation-email", ctx);
        Set<String> emailSet = new HashSet<>(emails);
        emailService.sendHtmlMessage(emailSet, "New Job In Your Field", htmlBody);
    }

}
