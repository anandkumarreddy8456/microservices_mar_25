package com.microserv.test.Test.of.Micro.Services.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Project {
    private long id;
    private long projectCode;
    private String projectName;
}
