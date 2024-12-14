package com.CCMe.Model;

import java.util.Date;

import com.CCMe.Entity.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Job extends AbstractEntity{
    
    private String field;

    private String company;

    private String location;

    private Integer views;

    private Integer ccs;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private User owner;

    // Add Budget and Start_Date
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    private Payment payment;

    public Job(String field, String company, String location, String description) {
        System.out.println("Constructor hit");
        this.field = field;
        this.company = company;
        this.location = location;
        this.views = 1;
        this.ccs = 1;
        this.description = description;
        this.status = Status.INCOMPLETE;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDate() {
        return created_at;
    }

    public void setDate(Date created_at) {
        this.created_at = created_at;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
