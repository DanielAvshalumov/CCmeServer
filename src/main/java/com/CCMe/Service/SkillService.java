package com.CCMe.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.CCMe.Configuration.SecurityUtil;
import com.CCMe.Model.Skill;
import com.CCMe.Model.User;
import com.CCMe.Repository.SkillRepository;
import com.CCMe.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SkillService {
    
    private final SkillRepository skillRepository;
    private final S3Service s3Service;

    // public Skill create(Skill skill) throws Exception {
    //     // skill.setUser(SecurityUtil.getAuthenticated());
    //     User user = SecurityUtil.getAuthenticated();
    //     user.addSkill(skill);
    //     userRepository.save(user);
    //     return skillRepository.save(skill);
    // }

    @Transactional
    public Skill addLicensePicture(Long skillId, MultipartFile file) {
        try {
            Skill skill = skillRepository.findById(skillId).get();
            String res = s3Service.uploadFile(file, skill);
            skill.setLicensePictureURL(res);
            return skillRepository.save(skill);
        } catch(Exception e) {
            e.getStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Skill> getUserSkills() {
        // try {
        //     List<Skill> skills = skillRepository.findAllByUser(SecurityUtil.getAuthenticated());
        //     return skills;
        // } catch (Exception e) {
        //     e.printStackTrace();
        //     return null;
        // }
        return null;
    }

    public List<String> getAllDistinctSkills() {
        // List<Skill> skills = skillRepository.findDistinctBy();
        // System.out.println("List of skills here:");
        // for(Skill skill : skills) {
        //     System.out.println(skill.getName());
        // }

        // return skills;
        List<String> findDistinctSkillNames = skillRepository.findDistinctSkillNames();
        return findDistinctSkillNames;
    }
}
