package com.CCMe.Controller;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/user")
    public ResponseEntity<List<Job>> getJobsByOwner(@RequestParam(name="id") Long id) throws Exception{
        return jobService.getJobsByOwner(id);
    }

    @GetMapping("/{field}")
    public ResponseEntity<List<Job>> getJobsByField(@PathVariable("field") String field) throws NotFoundException {
        return jobService.getJobsByField(field);
    }

    @PostMapping("/create")
    public ResponseEntity<Job> create(@RequestBody Job job) throws Exception {
        return jobService.create(job);
    }

    @PostMapping("/complete/{id}")
    public ResponseEntity<Job> complete(@PathVariable("id") Long id) {
        return jobService.complete(id);
    }
}
