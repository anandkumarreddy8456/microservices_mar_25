package com.microserv.test.Project_microservices.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project",uniqueConstraints ={
        @UniqueConstraint(columnNames = {"projectCode"})
})
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private long projectCode;
    @Column(nullable = false)
    private String projectName;
    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectCode=" + projectCode +
                ", projectName='" + projectName + '\'' +
                '}';
    }
}
