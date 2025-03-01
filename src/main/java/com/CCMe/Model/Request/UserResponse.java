package com.CCMe.Model.Request;

import java.util.List;
import java.util.stream.Collectors;

import com.CCMe.Model.Role;
import com.CCMe.Model.Skill;
import com.CCMe.Model.User;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private Role role;
    private String email;
    private String firstName;
    private String lastName;
    private String company;
    private boolean isContractor;
    private String profilePictureUrl;
    private String description;
    private String phoneNumber;
    private List<Skill> skills;
    // private String test;

    public UserResponse(User user) {
        this.id = user.getId();
        this.role = user.getRole();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.company = user.getCompany();
        this.isContractor = user.isContractor();
        this.profilePictureUrl = user.getProfilePictureUrl();
        this.description = user.getDescription();
        this.phoneNumber = user.getPhoneNumber();
        // this.test = user.getTest();
        // this.skills = user.getSkills().stream().map(skill -> {
        //     return new SkillResponse(skill);
        // }).collect(Collectors.toList());
        this.skills = user.getSkills();
    }
}
