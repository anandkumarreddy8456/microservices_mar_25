package com.anand.payload;

import com.anand.domain.BookingStatus;
import jakarta.persistence.ElementCollection;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookingDto {
    private Long id;
    private Long onlineAppId;
    private Long customerId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BookingStatus bookingStatus=BookingStatus.PENDING;
    private Set<Long> serviceIds;
    private int totalPrice;
}
