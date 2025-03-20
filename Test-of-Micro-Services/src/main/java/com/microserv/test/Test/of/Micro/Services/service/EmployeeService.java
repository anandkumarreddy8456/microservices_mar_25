package com.microserv.test.Test.of.Micro.Services.service;

import com.microserv.test.Test.of.Micro.Services.dto.Employee;
import com.microserv.test.Test.of.Micro.Services.payload.EmployeeDTO;

public interface EmployeeService {
    public EmployeeDTO saveEmployee(Employee emp);
    public EmployeeDTO getEmployeeById(long id);
}
