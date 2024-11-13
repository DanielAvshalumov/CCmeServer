package com.CCMe.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CCMe.Model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByField(String field);
}
