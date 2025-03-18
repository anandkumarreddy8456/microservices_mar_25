package com.anand.serviceOffering.serviceImpl;

import com.anand.serviceOffering.entity.ServiceOffering;
import com.anand.serviceOffering.payload.CategoryDto;
import com.anand.serviceOffering.payload.OnlineAppDto;
import com.anand.serviceOffering.payload.ServiceDto;
import com.anand.serviceOffering.repository.ServiceOfferingRepository;
import com.anand.serviceOffering.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceOfferingServiceImpl implements ServiceOfferingService {

    private final ServiceOfferingRepository serviceOfferingRepository;

    @Override
    public ServiceOffering createServiceOffering(OnlineAppDto onlineAppDto,
                                                 ServiceDto serviceDto,
                                                 CategoryDto categoryDto) {
        ServiceOffering serviceOffering=new ServiceOffering();
        serviceOffering.setImage(serviceDto.getImage());
        serviceOffering.setName(serviceDto.getName());
        serviceOffering.setOnlineAppId(onlineAppDto.getId());
        serviceOffering.setDescription(serviceDto.getDescription());
        serviceOffering.setPrice(serviceDto.getPrice());
        serviceOffering.setDuration(serviceDto.getDuration());
        serviceOffering.setCategoryId(categoryDto.getId());
        System.out.println(serviceOffering);
        return serviceOfferingRepository.save(serviceOffering);

    }

    @Override
    public ServiceOffering updateService(Long serviceId, ServiceOffering serviceOffering1) throws Exception {
        ServiceOffering serviceOffering=serviceOfferingRepository.findById(serviceId).orElse(null);
        if(serviceOffering == null){
            throw new Exception(" Serivce Not Exist");
        }
        serviceOffering.setImage(serviceOffering1.getImage());
        serviceOffering.setName(serviceOffering1.getName());

        serviceOffering.setDescription(serviceOffering1.getDescription());

        serviceOffering.setPrice(serviceOffering1.getPrice());
        serviceOffering.setDuration(serviceOffering1.getDuration());
        serviceOffering.setCategoryId(serviceOffering1.getCategoryId());
        return serviceOfferingRepository.save(serviceOffering);
    }

    @Override
    public Set<ServiceOffering> getAllServicesByOnlineApp(Long onlineAppId, Long categoryId) {
        Set<ServiceOffering> serviceOffering= serviceOfferingRepository.findByOnlineAppId(onlineAppId);
        if(categoryId!=null){
            serviceOffering=serviceOffering.stream().filter((service)->service.getCategoryId() !=null && service.getCategoryId().equals(categoryId)).collect(Collectors.toSet());
        }
        return serviceOffering;
    }

    @Override
    public Set<ServiceOffering> getServicesById(Set<Long> ids) {
        List<ServiceOffering> offeringList=serviceOfferingRepository.findAllById(ids);
        return new HashSet<>(offeringList);
    }

    @Override
    public ServiceOffering getByServiceById(Long serviceId) throws Exception {
        ServiceOffering serviceOffering=serviceOfferingRepository.findById(serviceId).orElse(null);
        if(serviceOffering == null){
            throw new Exception(" Serivce Not Exist");
        }
        return serviceOffering;
    }
}
