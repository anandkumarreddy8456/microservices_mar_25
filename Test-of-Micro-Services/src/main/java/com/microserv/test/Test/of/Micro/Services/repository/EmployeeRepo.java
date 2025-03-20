package com.microserv.test.Test.of.Micro.Services.repository;

import com.microserv.test.Test.of.Micro.Services.dto.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee,Long> {
}
