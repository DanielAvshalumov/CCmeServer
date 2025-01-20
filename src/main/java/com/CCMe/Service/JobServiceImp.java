package com.CCMe.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.jobrunr.scheduling.BackgroundJobRequest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.CCMe.Configuration.SecurityUtil;
import com.CCMe.Emails.SendJobAfterCreationEmail;
import com.CCMe.Model.CreateJobRequest;
import com.CCMe.Model.Job;
import com.CCMe.Model.Skill;
import com.CCMe.Model.Status;
import com.CCMe.Model.User;
import com.CCMe.Model.Request.GeocodeResponse;
import com.CCMe.Repository.JobRepository;
import com.CCMe.Repository.SkillRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobServiceImp implements JobService{
    private final JobRepository jobRepo;
    private final SkillRepository skillRepository;
    private final GeocodeService geocodeService;

    
    @Override
    public ResponseEntity<List<Job>> getAll() throws NotFoundException {
        return new ResponseEntity<>(jobRepo.findAll(),HttpStatus.OK);
    }

    // @Override
    // public ResponseEntity<List<Job>> getJobsByField(String field) throws NotFoundException {
    //     List<Job> arr = jobRepo.findByField(field);
    //     return new ResponseEntity<>(arr, HttpStatus.OK);
    // }

    @Override
    public ResponseEntity<Job> create(CreateJobRequest jobRequest) throws Exception{
        Job job = new Job(jobRequest.getStartDate(), jobRequest.getLocation(), jobRequest.getDescription());
        job.setOwner(SecurityUtil.getAuthenticated());
        job.setDate(new Date());
        // Get full List of Skills
        List<String> skillsToParse = new ArrayList<>();
        List<String> namesToAdd = jobRequest.getSkills();
        for(String name : namesToAdd) {
            skillsToParse.add(name);
        }
        List<String> allNames = skillRepository.findDistinctBy().stream().map(skill -> {
            return skill.getName();
        }).collect(Collectors.toList());
        // Filter for new skills
        namesToAdd.removeAll(allNames);
        List<Skill> skillsToAdd = namesToAdd.stream().map(skill -> {
            return new Skill(skill, "" ,0, null);
        }).collect(Collectors.toList());
        skillRepository.saveAll(skillsToAdd);
        Job res = jobRepo.save(job);
        // Send email alert to those with the skills
        GeocodeResponse _res = geocodeService.getCoordinates(jobRequest.getLocation());
        String latitude = Double.toString(_res.getResults().getFirst().getGeometry().getLocation().getLat());
        String longitude = Double.toString(_res.getResults().getFirst().getGeometry().getLocation().getLng());
        System.out.println("coordinates "+latitude+" "+longitude);
        String miniMap = UriComponentsBuilder.fromHttpUrl("https://maps.googleapis.com/maps/api/staticmap")
            .queryParam("markers",latitude+","+longitude)
            .queryParam("size","600x400")
            .queryParam("key","")
            .queryParam("zoom","14").toUriString();
        System.out.println(miniMap);
        sendJobAfterCreationEmail(skillsToParse,res,miniMap);
        return ResponseEntity.ok(job);
    }


    private void sendJobAfterCreationEmail(List<String> names, Job job, String map) {
        SendJobAfterCreationEmail sendJobAferCreationEmail = new SendJobAfterCreationEmail(names, job, map);
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
}
