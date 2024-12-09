package com.CCMe.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.CCMe.Model.Job;
import com.CCMe.Model.User;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    @Query(value = "SELECT a.contractor_id FROM applicant a WHERE a.sender_id = ?1", nativeQuery=true)
    List<Long> getIdsBySender(Long userId);
    List<Job> findByField(String field);
    List<Job> findByOwner(User user);
    Optional<Job> findById(Long id);   
}
