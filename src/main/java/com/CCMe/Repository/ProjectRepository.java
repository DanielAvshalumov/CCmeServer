package com.CCMe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CCMe.Model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    
}
