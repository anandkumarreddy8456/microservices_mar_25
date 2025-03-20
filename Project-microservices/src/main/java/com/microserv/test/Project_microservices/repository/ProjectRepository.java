package com.microserv.test.Project_microservices.repository;

import com.microserv.test.Project_microservices.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {
    Project findByProjectCode(long assignedProjectCode);
}
