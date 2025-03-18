package com.anand.serviceOffering.controller;

import com.anand.serviceOffering.entity.ServiceOffering;
import com.anand.serviceOffering.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
    @RequestMapping("/serviceOffering")
@RequiredArgsConstructor
public class ServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;
    @GetMapping("/serviceByOnlineAppId")
    public ResponseEntity<Set<ServiceOffering>> getServiceOfferingByOnlineAppId(@RequestParam Long onlineAppId,
                                                                                @RequestParam(required = false)Long categoryId){
        return new ResponseEntity<>(serviceOfferingService.getAllServicesByOnlineApp(onlineAppId,categoryId), HttpStatus.OK);
    }
    @GetMapping("/serviceById")
    public ResponseEntity<ServiceOffering> getServiceOfferingById(@RequestParam Long onlineAppId) throws Exception {

        return new ResponseEntity<>(serviceOfferingService.getByServiceById(onlineAppId), HttpStatus.OK);
    }
    @GetMapping("/listServiceByIds")
    public ResponseEntity<Set<ServiceOffering>> getServiceOfferingByIds(@RequestParam Set<Long> ids){
        return new ResponseEntity<>(serviceOfferingService.getServicesById(ids), HttpStatus.OK);
    }


}
