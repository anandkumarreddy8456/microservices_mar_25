package com.anand.serviceOffering.service;

import com.anand.serviceOffering.entity.ServiceOffering;
import com.anand.serviceOffering.payload.CategoryDto;
import com.anand.serviceOffering.payload.OnlineAppDto;
import com.anand.serviceOffering.payload.ServiceDto;

import java.util.List;
import java.util.Set;

public interface ServiceOfferingService {
    ServiceOffering createServiceOffering(OnlineAppDto onlineAppDto, ServiceDto serviceDto, CategoryDto categoryDto);
    ServiceOffering updateService(Long serviceId,ServiceOffering serviceOffering) throws Exception;
    Set<ServiceOffering> getAllServicesByOnlineApp(Long onlineAppId, Long categoryId);
    Set<ServiceOffering> getServicesById(Set<Long> ids);
    ServiceOffering getByServiceById(Long id) throws Exception;
}
