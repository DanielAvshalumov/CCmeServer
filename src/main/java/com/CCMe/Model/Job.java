package com.CCMe.Model;

import com.CCMe.Entity.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
@Entity
public class Job extends AbstractEntity{
    
    private String field;

    private String company;

    private String location;

    private Integer views;

    private Integer ccs;

    private String description;

    @ManyToOne
    private User owner;

    public Job(){}

    public Job(String field, String company, String location, String description, User owner) {
        this.field = field;
        this.company = company;
        this.location = location;
        this.views = 0;
        this.ccs = 0;
        this.description = description;
        this.owner = owner;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getCcs() {
        return ccs;
    }

    public void setCcs(Integer ccs) {
        this.ccs = ccs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
