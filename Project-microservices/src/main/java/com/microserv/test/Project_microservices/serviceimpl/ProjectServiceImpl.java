package com.microserv.test.Project_microservices.serviceimpl;

import com.microserv.test.Project_microservices.entity.Project;
import com.microserv.test.Project_microservices.repository.ProjectRepository;
import com.microserv.test.Project_microservices.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project saveProject(Project project) {

        // Add a debug statement to check the project details
        System.out.println("Saving project: " + project.toString());

        // Ensure projectName is set
//        if (project.getProjectName() == null) {
//            throw new IllegalArgumentException("Project name cannot be null");
//        }

        return projectRepository.save(project);
    }

    @Override
    public Project getProjectByCode(long projectCode) {

        return projectRepository.findByProjectCode(projectCode);
    }
}
