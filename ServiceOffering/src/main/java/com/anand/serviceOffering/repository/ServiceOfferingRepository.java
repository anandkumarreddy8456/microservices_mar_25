package com.anand.serviceOffering.repository;

import com.anand.serviceOffering.entity.ServiceOffering;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ServiceOfferingRepository extends JpaRepository<ServiceOffering,Long> {
    Set<ServiceOffering> findByOnlineAppId(Long id);
}
