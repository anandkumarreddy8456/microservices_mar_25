package com.microserv.test.Test.of.Micro.Services.serviceimpl;

import com.google.gson.Gson;
import com.microserv.test.Test.of.Micro.Services.FeignClient.ProjectFeignInterface;
import com.microserv.test.Test.of.Micro.Services.dto.Employee;
import com.microserv.test.Test.of.Micro.Services.dto.Project;
import com.microserv.test.Test.of.Micro.Services.payload.EmployeeDTO;
import com.microserv.test.Test.of.Micro.Services.repository.EmployeeRepo;
import com.microserv.test.Test.of.Micro.Services.service.EmployeeService;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Service
public class EmployeServiceImpl implements EmployeeService {

    @Autowired
    private ProjectFeignInterface projectFeignInterface;
    @Autowired
    private EmployeeRepo employeeRepo;


    @Override
    public EmployeeDTO saveEmployee(Employee emp) {
        Employee employee=employeeRepo.save(emp);
        System.out.println(employee);
        Response response=projectFeignInterface.getProjectByCode(employee.getEmployeeAssignedProject());
        String bodyContent="";
        try (InputStream inputStream = response.body().asInputStream()) {
            bodyContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson g=new Gson();
        Project project=g.fromJson(bodyContent,Project.class);
        EmployeeDTO employeeDTO=new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setEmployeeName(employee.getEmployeeName());
        employeeDTO.setEmployeeEmail(employee.getEmployeeEmail());
        employeeDTO.setEmployeeBaseLocation(employee.getEmployeeBaseLocation());
        employeeDTO.setProjectCode(project.getProjectCode());
        employeeDTO.setProjectName(project.getProjectName());
        return employeeDTO;
    }

    @Override
    public EmployeeDTO getEmployeeById(long id) {
        if(!employeeRepo.findById(id).isPresent()){
            return null;
        }
        Employee employee=employeeRepo.findById(id).get();
        Response response=projectFeignInterface.getProjectByCode(employee.getEmployeeAssignedProject());
        String bodyContent="";
        try (InputStream inputStream = response.body().asInputStream()) {
            bodyContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson g=new Gson();
        Project project=g.fromJson(bodyContent,Project.class);
        EmployeeDTO employeeDTO=new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setEmployeeName(employee.getEmployeeName());
        employeeDTO.setEmployeeEmail(employee.getEmployeeEmail());
        employeeDTO.setEmployeeBaseLocation(employee.getEmployeeBaseLocation());
        employeeDTO.setProjectCode(project.getProjectCode());
        employeeDTO.setProjectName(project.getProjectName());
        return employeeDTO;
    }
}
