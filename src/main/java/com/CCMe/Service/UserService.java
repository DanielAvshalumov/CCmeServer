package com.CCMe.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jobrunr.scheduling.BackgroundJobRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.CCMe.Configuration.SecurityUtil;
import com.CCMe.Emails.SendWelcomeEmail;
import com.CCMe.Model.Skill;
import com.CCMe.Model.User;
import com.CCMe.Model.VerificationCode;
import com.CCMe.Model.Request.CreateUserRequest;
import com.CCMe.Model.Request.UpdateUserRequest;
import com.CCMe.Model.Request.UserResponse;
import com.CCMe.Repository.SkillRepository;
import com.CCMe.Repository.UserRepository;
import com.CCMe.Repository.VerificationCodeRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final VerificationCodeRepository verificationRepository;
    private final SkillRepository skillRepository;
    private final S3Service s3Service;

    @Transactional
    public UserResponse create(@Valid CreateUserRequest req) {
        User user = new User(req);
        user.setSkills(new ArrayList<>());
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
    public UserResponse update(UpdateUserRequest updateUserRequest) throws Exception {
        User user = SecurityUtil.getAuthenticated();
        user = userRepository.getReferenceById(user.getId());
        BeanUtils.copyProperties(updateUserRequest,user,"id");
        return new UserResponse(userRepository.save(user));
    }

    public UserResponse getUser(Long id) {
        User user = userRepository.findById(id).get();
        return new UserResponse(user);
    }

    public UserResponse updateDescription(String description) throws Exception{
        User user = SecurityUtil.getAuthenticated();
        System.out.println(description);
        user.setDescription(description);
        User res = userRepository.save(user);
        return new UserResponse(res);
    }

    public UserResponse updateProfilePicture(MultipartFile file){
        try {
            User user = SecurityUtil.getAuthenticated();
            String res = s3Service.uploadFile(user.getId(), file);
            user.setProfilePictureUrl(res);
            userRepository.save(user);
            return new UserResponse(user);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
            ex.getStackTrace();
            return null;
        }
    }

    public List<UserResponse> searchContractors(String query) {
        List<User> contractors = userRepository.findAllByisContractorTrueAndfirstNameLike(query);
        List<UserResponse> res = contractors.stream().map(contractor -> {
            return new UserResponse(contractor);
        }).collect(Collectors.toList());
        
        return res;
    }

    @Transactional
    public User addSkill(Skill skill) {
        try {
            User user = SecurityUtil.getAuthenticated();
            
            user.addSkill(skillRepository.save(skill));
            userRepository.save(user);
            return user;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        
    }

    @Transactional
    public Skill updateSkillPicture(Long id, MultipartFile file) {
        try {
            User user = SecurityUtil.getAuthenticated();
            List<Skill> skills = user.getSkills();
            String res = s3Service.uploadFile(file, id);
            Skill _res = null;
            for(int i = 0; i < skills.size(); i++) {
                if(skills.get(i).getId() == id) {
                    _res = skills.get(i);
                    skills.get(i).setLicensePictureURL(res);
                    break;
                }
            }
            skillRepository.save(_res);
            userRepository.save(user);
            return _res;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        
    }
    
}
