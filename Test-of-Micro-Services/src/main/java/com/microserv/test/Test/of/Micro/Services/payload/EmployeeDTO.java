package com.microserv.test.Test.of.Micro.Services.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {
    private long id;
    private String employeeName;
    private String employeeEmail;
    private String employeeBaseLocation;
    private long projectCode;
    private String projectName;

}
