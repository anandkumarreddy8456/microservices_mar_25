package com.anand.OnlineApp.serviceImpl;

import com.anand.OnlineApp.entity.OnlineApp;
import com.anand.OnlineApp.payload.OnlineAppDto;
import com.anand.OnlineApp.payload.UserDto;
import com.anand.OnlineApp.repository.OnlineAppRepository;
import com.anand.OnlineApp.service.OnlineAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OnlineAppServiceImpl implements OnlineAppService {

    private final OnlineAppRepository onlineAppRepository;
    @Override
    public OnlineApp createOnlineApp(OnlineAppDto onlineAppDto, UserDto userDto) {
        OnlineApp onlineApp=new OnlineApp();
        onlineApp.setName(onlineAppDto.getName());
        onlineApp.setEmail(onlineAppDto.getEmail());
        onlineApp.setCity(onlineAppDto.getCity());
        onlineApp.setAddress(onlineAppDto.getAddress());
        onlineApp.setOpenTime(onlineAppDto.getOpenTime());
        onlineApp.setImages(onlineAppDto.getImages());
        onlineApp.setCloseTime(onlineAppDto.getCloseTime());
        onlineApp.setPhoneNumber(onlineAppDto.getPhoneNumber());
        onlineApp.setOwnerId(userDto.getId());

        return onlineAppRepository.save(onlineApp);
    }

    @Override
    public OnlineApp updateOnlineApp(long onlineAppId, OnlineAppDto onlineAppDto, UserDto userDto)throws Exception {

        Optional<OnlineApp> opt=onlineAppRepository.findById(onlineAppId);
        if(opt.isEmpty()){
            throw new Exception("OnlineApp not exist with Id"+onlineAppId);
        }
        OnlineApp onlineApp=opt.get();
        if(!onlineApp.getOwnerId().equals(userDto.getId())) {
            throw new Exception("UnAuthorized Person");
        }
        onlineApp.setName(onlineAppDto.getName());
        onlineApp.setEmail(onlineAppDto.getEmail());
        onlineApp.setCity(onlineAppDto.getCity());
        onlineApp.setAddress(onlineAppDto.getAddress());
        onlineApp.setOpenTime(onlineAppDto.getOpenTime());
        onlineApp.setImages(onlineAppDto.getImages());
        onlineApp.setCloseTime(onlineAppDto.getCloseTime());
        onlineApp.setPhoneNumber(onlineAppDto.getPhoneNumber());
        onlineApp.setOwnerId(userDto.getId());
        return onlineAppRepository.save(onlineApp);

    }

    @Override
    public List<OnlineApp> getAllOnlineApp() {
        return onlineAppRepository.findAll();
    }

    @Override
    public OnlineApp getOnlineAppById(Long onlineAppId) throws Exception {
        Optional<OnlineApp> onlineApp=onlineAppRepository.findById(onlineAppId);
        if(onlineApp.isEmpty()){
            throw new Exception("OnlineApp not exist with Id"+onlineAppId);
        }
        return onlineApp.get();
    }

    @Override
    public OnlineApp getOnlineAppByOwnerId(Long OwnerId)throws Exception {
        OnlineApp onlineApp=onlineAppRepository.findByOwnerId(OwnerId);
        if(onlineApp==null){
            throw new Exception("OnlineApp not exist with Owner Id"+OwnerId);
        }
        return onlineApp;
    }

    @Override
    public List<OnlineApp> searchOnlineApp(String city) {
        return onlineAppRepository.searchOnlineApp(city);
    }
}
