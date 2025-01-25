package com.CCMe.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.CCMe.Model.Skill;
import com.CCMe.Repository.SkillRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SkillService {
    
    private final SkillRepository skillRepository;

    public List<Skill> getAllDistinctSkills() {
        List<Skill> findDistinctSkills = skillRepository.findDistinctBy();
        return findDistinctSkills;
    }
}
