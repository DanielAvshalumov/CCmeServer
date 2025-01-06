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
    
    private String title;

    private String company;

    private String location;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private User owner;

    // Add Budget and Start_Date
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    private Payment payment;

    public Job(String title, String company, String location, String description) {
        System.out.println("Constructor hit");
        this.title = title;
        this.company = company;
        this.location = location;
        this.description = description;
        this.status = Status.INCOMPLETE;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
