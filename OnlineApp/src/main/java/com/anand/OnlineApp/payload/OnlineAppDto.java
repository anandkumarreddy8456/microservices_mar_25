package com.anand.OnlineApp.payload;



import lombok.Data;

import java.time.LocalTime;
import java.util.List;
@Data
public class OnlineAppDto {

    private Long id;

    private String name;

    private List<String> images;

    private String address;

    private String phoneNumber;

    private String email;

    private String city;

    private long ownerId;

    private LocalTime openTime;

    private LocalTime closeTime;
}
