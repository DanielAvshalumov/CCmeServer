package com.CCMe.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

import org.jobrunr.scheduling.BackgroundJobRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.CCMe.Configuration.SecurityUtil;
import com.CCMe.Emails.SendWelcomeEmail;
import com.CCMe.Model.User;
import com.CCMe.Model.VerificationCode;
import com.CCMe.Model.Request.CreateUserRequest;
import com.CCMe.Model.Request.UpdateUserRequest;
import com.CCMe.Model.Request.UserResponse;
import com.CCMe.Repository.UserRepository;
import com.CCMe.Repository.VerificationCodeRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final VerificationCodeRepository verificationRepository;

    @Transactional
    public UserResponse create(@Valid CreateUserRequest req) {
        User user = new User(req);
        user = userRepository.save(user);
        sendVerificationEmail(user);
        return new UserResponse(user);
    }

    private void sendVerificationEmail(User user) {
        VerificationCode verificationCode = new VerificationCode(user);
        user.setVerificationCode((verificationCode));
        verificationRepository.save(verificationCode);
        SendWelcomeEmail sendWelcomeEmail = new SendWelcomeEmail(user.getId());
        BackgroundJobRequest.enqueue(sendWelcomeEmail);
    }

    @Transactional
    public void verifyEmail(String code) {
        VerificationCode verificationCode = verificationRepository.findByCode(code);
        User user = verificationCode.getUser();
        user.setVerified(true);
        userRepository.save(user);
        verificationRepository.delete(verificationCode);
    }

    @Transactional
    public void forgotPassword(String email) {
        User user = userRepository.findByEmail(email);
        
    }


    public List<UserResponse> getAllNonContractors(boolean isContractor) {
        List<UserResponse> nonContractors = 
        userRepository.findByIsContractor(isContractor)
            .stream()
            .map(user -> {return new UserResponse(user);})
            .collect(Collectors.toList());
        return nonContractors;
    }

    public UserResponse update(UpdateUserRequest updateUserRequest) throws Exception {
        User user = SecurityUtil.getAuthenticated();
        user = userRepository.getReferenceById(user.getId());
        BeanUtils.copyProperties(updateUserRequest,user,"id");
        return new UserResponse(userRepository.save(user));
    }
    
}
