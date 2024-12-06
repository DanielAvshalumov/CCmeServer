package com.CCMe.Model;

import java.util.Date;

import com.CCMe.Entity.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Applicant extends AbstractEntity{
    @ManyToOne
    private User sender;
    @ManyToOne
    private Job contractor;
    @Enumerated(EnumType.STRING)
    private Decision decision;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;
}
