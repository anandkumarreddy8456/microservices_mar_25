package com.microserv.test.Test.of.Micro.Services.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="employee",uniqueConstraints = {@UniqueConstraint(columnNames = "employeeEmail")})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String employeeName;
    @Column(nullable = false)
    private String employeeEmail;
    @Column(nullable = false)
    private long employeeAssignedProject;
    @Column(nullable = false)
    private String employeeBaseLocation;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", employeeName='" + employeeName + '\'' +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", employeeAssignedProject=" + employeeAssignedProject +
                ", employeeBaseLocation='" + employeeBaseLocation + '\'' +
                '}';
    }
}
