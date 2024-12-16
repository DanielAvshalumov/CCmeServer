package com.CCMe.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.CCMe.Model.Applicant;
import com.CCMe.Model.Job;
import com.CCMe.Model.User;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant,Long>{
    List<Applicant> findAllByContractorId(Long id);
    // @Query(value="SELECET COUNT(DISTINCT contractor_id,sender_id) FROM applicant where contractor_id=?1 and sender_id=?2", nativeQuery=true)
    // Integer existsApplicant(Long senderId, Long contractorId);
    Boolean existsByContractorAndSender(Job contractor, User sender);
}
