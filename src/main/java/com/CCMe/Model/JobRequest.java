package com.CCMe.Model;

import java.util.Date;

import com.CCMe.Entity.AbstractEntity;

import lombok.Data;

@Data
public class JobRequest extends AbstractEntity{
    
    private User sender;
    private User contractor;
    private Date created_at;
}
