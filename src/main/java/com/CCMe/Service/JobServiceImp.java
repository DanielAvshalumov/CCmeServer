package com.CCMe.Service;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.CCMe.Configuration.SecurityUtil;
import com.CCMe.Model.Job;
import com.CCMe.Model.JobResponse;
import com.CCMe.Model.Status;
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
        job.setStatus(Status.INCOMPLETE);
        job.setDate(new Date());
        Job res = jobRepo.save(job);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Job>> getJobsByOwner(Long id) throws Exception {
        User user = SecurityUtil.getAuthenticated();
        List<Job> jobs = jobRepo.findByOwner(user);
        return new ResponseEntity<>(jobs,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Job> complete(Long id) {
        Job job = jobRepo.findById(id).get();
        job.setStatus(Status.ONGOING);
        return new ResponseEntity<>(jobRepo.save(job),HttpStatus.OK);
    }

    @Override
    public List<Object> getJobsByApplicant(Long id) {
        // List<JobResponse> jobs = jobRepo.getIdsBySender(id).stream().map(_id -> {
        //     Job job = jobRepo.findById(_id).get();
        //     JobResponse res = new JobResponse(
        //         job.getId(),
        //         job.getField(),
        //         job.getCompany(),
        //         job.getLocation(),
        //         job.getOwner()
        //     );
        //     return res;
        // }).collect(Collectors.toList());
        // List<JobResponse> jobs = jobRepo.getProfileJobs(id).stream().map(job -> {
        //     return new JobResponse(

        //     );
        // }).collect(Collectors.toList());
        List<Object> jobs = jobRepo.getProfileJobs(id);
        System.out.println(jobs);
        return jobs;
    }
}
