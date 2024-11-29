package com.CCMe.Service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;

import com.CCMe.Model.Job;

public interface JobService {
    public ResponseEntity<List<Job>> getAll() throws NotFoundException;
    public ResponseEntity<List<Job>> getJobsByField(String field) throws NotFoundException;
    public ResponseEntity<Job> create(Job job) throws Exception;
    public ResponseEntity<List<Job>> getJobsByOwner(Long id) throws Exception;
    public ResponseEntity<Job> complete(Long jobId);
    public ResponseEntity<Job> apply(Long id) throws Exception;
}