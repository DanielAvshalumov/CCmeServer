package com.CCMe.Service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;

import com.CCMe.Model.CreateJobRequest;
import com.CCMe.Model.Job;

public interface JobService {
    public ResponseEntity<List<Job>> getAll() throws NotFoundException;
    // public ResponseEntity<List<Job>> getJobsByField(String field) throws NotFoundException;
    public ResponseEntity<Job> create(CreateJobRequest job) throws Exception;
    public ResponseEntity<List<Job>> getJobsByOwner() throws Exception;
    public ResponseEntity<Job> complete(Long jobId);
    public List<Object> getJobsByApplicant(Long id);
    public ResponseEntity<Job> getJobById(Long id);
}