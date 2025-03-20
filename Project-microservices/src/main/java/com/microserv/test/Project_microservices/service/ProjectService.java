package com.microserv.test.Project_microservices.service;

import com.microserv.test.Project_microservices.entity.Project;
import org.springframework.stereotype.Service;

@Service
public interface ProjectService {
    public Project saveProject(Project project);
    public Project getProjectByCode(long projectCode);
}
