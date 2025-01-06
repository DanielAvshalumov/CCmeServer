package com.CCMe.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.CCMe.Model.Job;
import com.CCMe.Model.User;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    @Query(value = """
        SELECT j.id 
            FROM applicant a JOIN job j ON a.contractor_id=j.id 
            WHERE sender_id = ?1
        """,
        nativeQuery=true)
    List<Long> getIdsBySender(Long userId);

    @Query(value = 
    "SELECT j.id, j.field, j.company, j.location, a.decision, j.status, j.first_name as owner " +
    "FROM applicant a " +
    "JOIN (" +
        "SELECT _j.id,_j.field,_j.company,_j.location,_j.status,u.first_name "+
        "FROM job _j join user u "+
        "ON _j.owner_id = u.id "+
    ") j "+
    "ON a.contractor_id=j.id "+
    "WHERE sender_id=:senderId;",
    nativeQuery = true)
    List<Object> getProfileJobs(@Param("senderId") Long senderId);

    // @Query("SELECT new com.CCMe.Model.JobResponse(j.id, j.field, j.company, j.location, a.decision, a.status) " +
    //         "FROM Applicant a " +
    //         "JOIN a.contractor j " +
    //         "WHERE a.sender.id = :senderId")
    // List<JobResponse> getProfileJobs(@Param("senderId") Long senderId);



    List<Job> findByOwner(User user);
    Optional<Job> findById(Long id);   
}
