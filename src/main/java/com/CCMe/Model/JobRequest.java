package com.CCMe.Model;

import java.util.Date;

import com.CCMe.Entity.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
@Entity
public class JobRequest extends AbstractEntity{
    
    private User sender;
    private User contractor;
    @Enumerated(EnumType.STRING)
    private Decision decision;
    private Date created_at;
}
