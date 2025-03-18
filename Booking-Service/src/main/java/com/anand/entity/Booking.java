package com.anand.entity;

import com.anand.domain.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long onlineAppId;
    private Long customerId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BookingStatus bookingStatus=BookingStatus.PENDING;
    @ElementCollection
    private Set<Long> serviceIds;
    private int totalService;
}
