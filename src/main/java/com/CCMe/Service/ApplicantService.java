package com.CCMe.Service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.CCMe.Configuration.SecurityUtil;
import com.CCMe.Model.Applicant;
import com.CCMe.Model.Decision;
import com.CCMe.Model.Job;
import com.CCMe.Model.User;
import com.CCMe.Repository.ApplicantRepository;
import com.CCMe.Repository.JobRepository;
import com.CCMe.Repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicantService {
    
    private final ApplicantRepository applicantRepository;
    private final JobRepository jobRepository;
    
    @Transactional
    public Applicant create(Long id) throws Exception {
        Job contractor = jobRepository.findById(id).get();
        User sender = SecurityUtil.getAuthenticated();
        Applicant applicant = new Applicant(sender, contractor, Decision.UNDECIDED, new Date());
        return applicantRepository.save(applicant);
    }

    public List<Applicant> getApplicantsByJob(Long id) {
        List<Applicant> applicants = applicantRepository.findAllByContractorId(id);
        return applicants;
    }
}