package com.CCMe.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CCMe.Model.Applicant;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant,Long>{
    List<Applicant> findAllByContractorId(Long id);
}
