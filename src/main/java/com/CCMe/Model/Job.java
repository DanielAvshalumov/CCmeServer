package com.CCMe.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String field;

    private String company;

    private String location;

    private Integer views;

    private Integer ccs;

    private String description;

    public Job(){}

    public Job(String field, String company, String location, Integer views, Integer ccs, String description) {
        this.field = field;
        this.company = company;
        this.location = location;
        this.views = views;
        this.ccs = ccs;
        this.description = description;
    }

    public Long getId() {
        return id;
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
        this.description = description;    } 
}
