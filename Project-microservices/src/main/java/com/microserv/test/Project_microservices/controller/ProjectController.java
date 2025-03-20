package com.microserv.test.Project_microservices.controller;

import com.microserv.test.Project_microservices.entity.Project;
import com.microserv.test.Project_microservices.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping("/save")
    public Project saveProject(@RequestBody Project project){
        System.out.println("Received project: " + project.toString());
        return projectService.saveProject(project);
    }

    @GetMapping("/get")
    public Project getProjectByCode(@RequestParam long projectCode){
        return projectService.getProjectByCode(projectCode);
    }

}
