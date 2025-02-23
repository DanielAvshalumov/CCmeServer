package com.CCMe.Service;

import org.springframework.stereotype.Service;

import com.CCMe.Model.Project;
import com.CCMe.Repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Project create(Project _project) {
        return  projectRepository.save(_project);
        
    }
    
}
