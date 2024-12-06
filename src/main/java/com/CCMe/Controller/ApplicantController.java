package com.CCMe.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CCMe.Model.Applicant;
import com.CCMe.Service.ApplicantService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/applicants")
@RequiredArgsConstructor
public class ApplicantController {

    private final ApplicantService applicantService;

    @GetMapping("/job/{id}")
    public ResponseEntity<List<Applicant>> getApplicantsByJob(@PathVariable("id") Long id) {
        return ResponseEntity.ok(applicantService.getApplicantsByJob(id));
    }
    
    @PostMapping("/create/{id}")
    public ResponseEntity<Applicant> createApplicant(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok(applicantService.create(id));
    }
}