package com.CCMe.Service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.CCMe.Configuration.SecurityUtil;
import com.CCMe.Model.Job;
import com.CCMe.Model.User;
import com.CCMe.Repository.JobRepository;

@Service
public class JobServiceImp implements JobService{
    private JobRepository jobRepo;

    public JobServiceImp(JobRepository jobRepo) {
        this.jobRepo = jobRepo;
    }

    @Override
    public ResponseEntity<List<Job>> getAll() throws NotFoundException {
        return new ResponseEntity<>(jobRepo.findAll(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Job>> getJobsByField(String field) throws NotFoundException {
        List<Job> arr = jobRepo.findByField(field);
        return new ResponseEntity<>(arr, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Job> create(Job job) throws Exception{
        job.setOwner(SecurityUtil.getAuthenticated());
        Job res = jobRepo.save(job);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Job>> getJobsByOwner(Long id) throws Exception {
        User user = SecurityUtil.getAuthenticated();
        List<Job> jobs = jobRepo.findByOwner(user);
        return new ResponseEntity<>(jobs,HttpStatus.OK);
    }   
    
}
