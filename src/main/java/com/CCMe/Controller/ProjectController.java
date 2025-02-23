package com.CCMe.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CCMe.Model.Project;
import com.CCMe.Service.ProjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/create")
    public ResponseEntity<Project> create(@RequestBody Project project) {
        Project res = projectService.create(project);
        return ResponseEntity.ok(res);
    }
}
