package com.anand.payment.payload;


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

    private Set<Long> serviceIds;
    private int totalPrice;
}
