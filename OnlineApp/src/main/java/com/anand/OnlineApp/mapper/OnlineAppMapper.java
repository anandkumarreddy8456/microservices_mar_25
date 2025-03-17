package com.anand.OnlineApp.mapper;

import com.anand.OnlineApp.entity.OnlineApp;
import com.anand.OnlineApp.payload.OnlineAppDto;

public class OnlineAppMapper {

    public static OnlineAppDto mapToDto(OnlineApp onlineApp){
        OnlineAppDto onlineAppDto=new OnlineAppDto();
        onlineAppDto.setId(onlineApp.getId());
        onlineAppDto.setName(onlineApp.getName());
        onlineAppDto.setEmail(onlineApp.getEmail());
        onlineAppDto.setCity(onlineApp.getCity());
        onlineAppDto.setAddress(onlineApp.getAddress());
        onlineAppDto.setOpenTime(onlineApp.getOpenTime());
        onlineAppDto.setImages(onlineApp.getImages());
        onlineAppDto.setCloseTime(onlineApp.getCloseTime());
        onlineAppDto.setPhoneNumber(onlineApp.getPhoneNumber());
        onlineAppDto.setOwnerId(onlineApp.getOwnerId());
        return onlineAppDto;
    }
}
