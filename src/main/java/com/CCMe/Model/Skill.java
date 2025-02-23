package com.CCMe.Model;

import org.springframework.lang.Nullable;

import com.CCMe.Entity.AbstractEntity;

import jakarta.persistence.Entity;
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
    private String licensePictureURL;

    @Nullable
    private Integer yearsExperience;

}
