package com.anand.repository;

import com.anand.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findByCustomerId(Long id);
    List<Booking> findByOnlineAppId(Long id);
}
