package com.anand.serviceOffering.controller;

import com.anand.serviceOffering.entity.ServiceOffering;
import com.anand.serviceOffering.payload.CategoryDto;
import com.anand.serviceOffering.payload.OnlineAppDto;
import com.anand.serviceOffering.payload.ServiceDto;
import com.anand.serviceOffering.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/OnlineOwnerService")
@RequiredArgsConstructor
public class OnlineAppServiceOfferingsOwnerController {
    private final ServiceOfferingService serviceOfferingService;
    @PostMapping("/createService")
    public ResponseEntity<ServiceOffering> createService(@RequestBody ServiceDto serviceDto ){
        OnlineAppDto onlineAppDto=new OnlineAppDto();
        onlineAppDto.setId(1L);
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setId(1L);
    return  new ResponseEntity<>(serviceOfferingService.createServiceOffering(onlineAppDto,serviceDto,categoryDto), HttpStatus.OK);
    }
    @PostMapping("/updateService")
    public  ResponseEntity<ServiceOffering> updateService(@RequestParam Long id,@RequestBody ServiceOffering serviceOffering) throws Exception {

        return new ResponseEntity<>(serviceOfferingService.updateService(id,serviceOffering),HttpStatus.OK);
    }

}
