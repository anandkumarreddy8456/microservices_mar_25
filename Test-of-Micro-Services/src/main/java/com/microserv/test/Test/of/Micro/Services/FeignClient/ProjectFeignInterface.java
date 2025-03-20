package com.microserv.test.Test.of.Micro.Services.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:8081",value = "feign-client",path = "/project")
public interface ProjectFeignInterface {
    @GetMapping("/get")
    feign.Response getProjectByCode(@RequestParam long projectCode);

}
