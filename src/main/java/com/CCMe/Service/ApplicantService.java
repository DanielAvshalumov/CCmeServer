package com.CCMe.Service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.CCMe.Configuration.SecurityUtil;
import com.CCMe.Model.Applicant;
import com.CCMe.Model.Decision;
import com.CCMe.Model.Job;
import com.CCMe.Model.Status;
import com.CCMe.Model.User;
import com.CCMe.Repository.ApplicantRepository;
import com.CCMe.Repository.JobRepository;

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
        // Owners can't apply to their own jobs, and contractor+sender = candidate key
        if(contractor.getOwner().getId() == sender.getId() || applicantRepository.existsByContractorAndSender(contractor,sender)) {
            return null;
        }
        
        Applicant applicant = new Applicant(sender, contractor, Decision.UNDECIDED, new Date());
        return applicantRepository.save(applicant);
    }

    public List<Applicant> getApplicantsByJob(Long id) {
        List<Applicant> applicants = applicantRepository.findAllByContractorId(id);
        return applicants;
    }

    public Applicant decide(Long id, Decision decision) {
        Applicant app = applicantRepository.findById(id).get();
        app.setDecision(decision);
        if(decision == Decision.ACCEPTED) {
            app.getContractor().setStatus(Status.ONGOING);
        }
        return applicantRepository.save(app);
    }

    public List<Applicant> getApplicantsByUser(Long id) {
        try {
            User user = SecurityUtil.getAuthenticated();
            List<Applicant> applicants = applicantRepository.findAllBySender(user);
            return applicants;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        
    }
}
