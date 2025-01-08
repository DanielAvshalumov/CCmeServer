package com.CCMe.Emails;

import java.util.ArrayList;
import java.util.List;
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
    private final ApplicationProperties applicationProperties;

    @Override
    public void run(SendJobAfterCreationEmail jobRequest) throws Exception {
        sendJobAfterCreationEmail(jobRequest.getEmails(), jobRequest.getJob());
    }
    
    private void sendJobAfterCreationEmail(List<String> names, Job job) {
        List<String> emails = new ArrayList<>();
        List<Skill> userSkills = skillRepository.findByNameIn(names);
        List<User> usersToSend = userSkills.stream().map(user -> {
            return user.getUser();
        }).collect(Collectors.toList());
        System.out.println(userSkills.size());
        for(int i = 0; i < userSkills.size(); i++) {
            try {
                System.out.println("UserSkills " + userSkills.get(i).getName());
                System.out.println("UsersToSend " + usersToSend.get(i).getEmail());
                emails.add(usersToSend.get(i).getEmail());
            } catch(NullPointerException ex) {
                System.out.println("Adding Skill: " + userSkills.get(i).getName());
                // TODO: Implement logic new Skills and who to send these jobs to 
            }
        }
        log.info("Will send an email with the following email: {}",usersToSend);
        Context ctx = new Context();
        String applicationLink = applicationProperties.getBaseUrl()+"/applicants/create/"+job.getId();
        ctx.setVariable("company", job.getCompany());
        ctx.setVariable("description", job.getDescription());
        ctx.setVariable("applicationLink", applicationLink);
        String htmlBody = templateEngine.process("job-after-creation-email", ctx);
        emailService.sendHtmlMessage(emails, "New Job In Your Field", htmlBody);
    }

}
