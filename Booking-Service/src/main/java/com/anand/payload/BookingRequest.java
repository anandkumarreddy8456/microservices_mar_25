package com.anand.payload;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
@Data
public class BookingRequest {

    private LocalDateTime startTIme;
    private LocalDateTime endTime;
    private Set<Long> serviceIds;
}
