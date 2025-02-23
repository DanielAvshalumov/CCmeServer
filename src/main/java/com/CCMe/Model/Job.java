package com.CCMe.Model;

import java.util.Date;
import java.util.Set;

import org.springframework.lang.Nullable;

import com.CCMe.Entity.AbstractEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    private Date startDate;

    private Payment payment;

    @OneToMany(mappedBy = "job", fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    private Set<JobImage> jobImages;

    public Job(Date startDate, String location, String description) {
        System.out.println("Constructor hit");
        this.startDate = startDate;
        this.location = location;
        this.description = description;
        this.status = Status.INCOMPLETE;
    }

    public Set<JobImage> getJobImages() {
        return jobImages;
    }

    public JobImage addJobImage(String url) {
        JobImage res = new JobImage();
        res.setJob(this);
        res.setIamge(url);
        jobImages.add(res);
        return res;
    }

    public void setJobImages(Set<JobImage> jobImages) {
        this.jobImages = jobImages;
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

    public Date getStarDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
