package com.CCMe.Controller;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CCMe.Model.Job;
import com.CCMe.Service.JobService;

@RestController
@RequestMapping("/jobs")
public class JobController {
    JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("")
    public ResponseEntity<List<Job>> getAllJobs() throws NotFoundException {
        return jobService.getAll();
    }

    @GetMapping("/{field}")
    public ResponseEntity<List<Job>> getJobsByField(@PathVariable("field") String field) throws NotFoundException {
        return jobService.getJobsByField(field);
    }
}
