package com.CCMe.Model;

import java.util.Set;

import com.CCMe.Entity.AbstractEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Project extends AbstractEntity{
    String location;
    String type;
    @OneToOne
    User generalContractor;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<Job> subcontracts;
    String licenseUrl;
}
