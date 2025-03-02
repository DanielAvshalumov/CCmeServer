package com.CCMe.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jobrunr.scheduling.BackgroundJobRequest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.CCMe.Configuration.SecurityUtil;
import com.CCMe.Emails.SendJobAfterCreationEmail;
import com.CCMe.Model.CreateJobRequest;
import com.CCMe.Model.Job;
import com.CCMe.Model.JobImage;
import com.CCMe.Model.Skill;
import com.CCMe.Model.Status;
import com.CCMe.Model.User;
import com.CCMe.Model.Request.GeocodeResponse;
import com.CCMe.Repository.JobImageRepository;
import com.CCMe.Repository.JobRepository;
import com.CCMe.Repository.SkillRepository;
import com.CCMe.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobServiceImp implements JobService{
    private final JobRepository jobRepo;
    private final UserRepository userRepository;
    private final GoogleService googleService;
    private final S3Service s3Service;
    private final JobImageRepository jobImageRepository;
    
    @Override
    public ResponseEntity<List<Job>> getAll() throws NotFoundException {
        return new ResponseEntity<>(jobRepo.findAll(),HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<Job> create(CreateJobRequest jobRequest) throws Exception{
        Job job = new Job(jobRequest.getStartDate(), jobRequest.getLocation(), jobRequest.getDescription());
        job.setTitle(jobRequest.getTitle());
        job.setOwner(SecurityUtil.getAuthenticated());
        job.setDate(new Date());
        List<String> skillNames = jobRequest.getSkills();
        List<String> emails = userRepository.getUsersInSkill(skillNames);
        
        Job res = jobRepo.save(job);
        String miniMap = googleService.getMiniMap(jobRequest.getLocation());
        
        sendJobAfterCreationEmail(emails,res,miniMap);
        return ResponseEntity.ok(job);
    }


    private void sendJobAfterCreationEmail(List<String> emails, Job job, String map)  {
        SendJobAfterCreationEmail sendJobAferCreationEmail = new SendJobAfterCreationEmail(emails, job.getId(), map, job.getCompany(), job.getDescription());
        BackgroundJobRequest.enqueue(sendJobAferCreationEmail);
    }

    @Override
    public ResponseEntity<List<Job>> getJobsByOwner() throws Exception {
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

    @Override
    public ResponseEntity<Job> getJobById(Long id) {
        Job job = jobRepo.findById(id).get();
        return ResponseEntity.ok(job);
    }

    @Override
    public JobImage uploadJobImage(MultipartFile file, Long jobId) {
        Job job = jobRepo.findById(jobId).get();
        String res = s3Service.uploadJobImage(jobId,file);
        JobImage jobImage = job.addJobImage(res);
        jobRepo.save(job);
        return jobImage;
        // return image;
    }

    public List<JobImage> getJobImages(int limit) {
        List<JobImage> res = jobImageRepository.findAll();
        return res;
    }
}
