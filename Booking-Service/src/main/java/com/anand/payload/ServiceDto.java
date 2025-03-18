package com.anand.payload;

import lombok.Data;

@Data
public class ServiceDto {
    private Long id;
   
    private String name;
   
    private String description;
   
    private int price;
   
    private int duration;
   
    private Long OnlineAppId;
   
    private Long categoryId;
    private String image;
}
