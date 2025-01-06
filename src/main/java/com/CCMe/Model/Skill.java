package com.CCMe.Model;

import org.springframework.lang.Nullable;

import com.CCMe.Entity.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Skill extends AbstractEntity{

    private String name;

    @Nullable
    private Integer yearsExperience;

    @ManyToOne
    @JsonIgnore
    @Nullable
    private User user;
}
