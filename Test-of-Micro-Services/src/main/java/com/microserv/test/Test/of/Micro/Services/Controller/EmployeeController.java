package com.microserv.test.Test.of.Micro.Services.Controller;

import com.microserv.test.Test.of.Micro.Services.dto.Employee;
import com.microserv.test.Test.of.Micro.Services.payload.EmployeeDTO;
import com.microserv.test.Test.of.Micro.Services.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/save")
    public EmployeeDTO saveEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }
    @GetMapping("/get")
    public EmployeeDTO getEmployye(@RequestParam long id){
        return employeeService.getEmployeeById(id);
    }
}
