package com.CCMe.Service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;

import com.CCMe.Model.Job;

public interface JobService {
    public ResponseEntity<List<Job>> getAll() throws NotFoundException;
    public ResponseEntity<List<Job>> getJobsByField(String field) throws NotFoundException;
}