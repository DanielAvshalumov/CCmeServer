package com.CCMe.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.CCMe.Entity.AbstractEntity;
import com.CCMe.Model.Request.CreateUserRequest;
import com.CCMe.utils.ApplicationContextProvider;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class User extends AbstractEntity implements UserDetails{
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String company;
    private String phoneNumber;
    private boolean isContractor;
    private boolean verified;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String profilePictureUrl;
    @Column(columnDefinition = "TEXT")
    private String description;
    @OneToOne(mappedBy = "user")
    private VerificationCode verificationCode;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Skill> skills;    
    // private String test;

    public User(CreateUserRequest userReq) {
        PasswordEncoder passwordEncoder = ApplicationContextProvider.bean(PasswordEncoder.class);
        this.email = userReq.getEmail();
        this.password = passwordEncoder.encode(userReq.getPassword());   
        this.firstName = userReq.getFirstName();
        this.lastName = userReq.getLastName();
        this.company = userReq.getCompany();
        this.role = Role.USER;
        this.isContractor = userReq.isContractor();
        this.phoneNumber = userReq.getPhoneNumber();
        // this.skills = new ArrayList<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_"+role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

}
