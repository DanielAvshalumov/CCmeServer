package com.CCMe.Controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.CCMe.Model.Job;
import com.CCMe.Model.JobImage;
import com.CCMe.Model.CreateJobRequest;
import com.CCMe.Model.Status;
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

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable("id") Long id) {
        return jobService.getJobById(id);
    }

    // @GetMapping("") {
    // public ResponseEntity<List<Job>> getJobsByStatus(@RequestParam(name="status") Status status) {
    //     return jobService.getJobsByStatus
    // }
    // }

    @GetMapping("/user")
    public ResponseEntity<List<Job>> getJobsByOwner() throws Exception{
        return jobService.getJobsByOwner();
    }

    @GetMapping("/sender/{id}")
    public ResponseEntity<List<Object>>getJobsByApplicant(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok(jobService.getJobsByApplicant(id));
    }

    // @GetMapping("/{field}")
    // public ResponseEntity<List<Job>> getJobsByField(@PathVariable("field") String field) throws NotFoundException {
    //     return jobService.getJobsByField(field);
    // }

    @PostMapping("/create")
    public ResponseEntity<Job> create(@RequestBody CreateJobRequest job) throws Exception {
        return jobService.create(job);
    }

    @PostMapping("/complete/{id}")
    public ResponseEntity<Job> complete(@PathVariable("id") Long id) {
        return jobService.complete(id);
    }

    @PatchMapping("/{jobId}/upload-image")
    public ResponseEntity<JobImage> uploadJobImage(@RequestParam("file") MultipartFile file, @PathVariable("jobId") Long jobId) throws Exception {
        JobImage jobImage = jobService.uploadJobImage(file, jobId);
        return ResponseEntity.ok(jobImage);
    }

    @GetMapping("/job-images")
    public ResponseEntity<List<JobImage>> getJobImages() {
        List<JobImage> images = jobService.getJobImages(5);
        return ResponseEntity.ok(images);
    }
}
