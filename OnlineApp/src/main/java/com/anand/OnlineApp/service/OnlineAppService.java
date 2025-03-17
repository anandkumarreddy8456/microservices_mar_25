package com.anand.OnlineApp.service;

import com.anand.OnlineApp.entity.OnlineApp;
import com.anand.OnlineApp.payload.OnlineAppDto;
import com.anand.OnlineApp.payload.UserDto;

import java.util.List;

public interface OnlineAppService {
    OnlineApp createOnlineApp(OnlineAppDto onlineAppDto, UserDto userDto);
    OnlineApp updateOnlineApp(long OnlineAppId,OnlineAppDto onlineAppDto, UserDto userDto)throws Exception;
    List<OnlineApp> getAllOnlineApp();
    OnlineApp getOnlineAppById(Long OnlineAppId) throws Exception;
    OnlineApp getOnlineAppByOwnerId(Long OwnerId) throws Exception;
    List<OnlineApp> searchOnlineApp(String city);
}
