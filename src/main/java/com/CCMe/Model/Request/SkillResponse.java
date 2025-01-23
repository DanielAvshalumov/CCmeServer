package com.CCMe.Model.Request;

import com.CCMe.Model.Skill;

import lombok.Data;

@Data
public class SkillResponse {
    private Long id;
    private String name;
    private String licensePictureURL;
    private Integer yearsExperience;

    public SkillResponse(Skill skill) {
        this.id = skill.getId();
        this.name = skill.getName();
        this.licensePictureURL = skill.getLicensePictureURL();
        this.yearsExperience = skill.getYearsExperience();
    }
}
