package com.anand.entity;

import lombok.Data;

@Data
public class OnlineAppReport {
    private Long OnlineAppId;
    private String OnlineAppName;
    private Double totalEarnings;
    private int totalBookings;
    private  Integer cancelBookings;
    private Double totalRefund;

}
