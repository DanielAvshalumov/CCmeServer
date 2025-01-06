package com.CCMe.Emails;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.jobrunr.jobs.lambdas.JobRequestHandler;
import org.springframework.stereotype.Component;

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

    @Override
    public void run(SendJobAfterCreationEmail jobRequest) throws Exception {
        sendJobAfterCreationEmail(jobRequest.getEmails());
    }
    
    private void sendJobAfterCreationEmail(List<String> names) {
        List<String> emails = new ArrayList<>();
        List<Skill> userSkills = skillRepository.findByNameIn(names);
        List<User> usersToSend = userSkills.stream().map(user -> {
            return user.getUser();
        }).collect(Collectors.toList());
        System.out.println(userSkills.size());
        for(int i = 0; i < userSkills.size(); i++) {
            System.out.println("UserSkills " + userSkills.get(i).getName());
            System.out.println("UsersToSend " + usersToSend.get(i).getEmail());
            emails.add(usersToSend.get(i).getEmail());
        }
        log.info("Will send an email with the following email: {}",usersToSend);
        emailService.sendSimpleEmail(emails, "New Job Alert", "Check out this new job");
    }

}
