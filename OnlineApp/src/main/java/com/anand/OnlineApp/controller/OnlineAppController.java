package com.anand.OnlineApp.controller;
import com.anand.OnlineApp.mapper.OnlineAppMapper;
import com.anand.OnlineApp.payload.OnlineAppDto;
import com.anand.OnlineApp.payload.UserDto;
import com.anand.OnlineApp.service.OnlineAppService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/onlineApp")
@RequiredArgsConstructor
public class OnlineAppController {
    private final OnlineAppService onlineAppService;
    @PostMapping("/createOnlineApp")
    public ResponseEntity<OnlineAppDto> createOnlineApp(@RequestBody OnlineAppDto onlineAppDto){
        UserDto userDto=new UserDto();
        userDto.setId(1L);
        return new ResponseEntity<>(OnlineAppMapper.mapToDto(onlineAppService.createOnlineApp(onlineAppDto,userDto)), HttpStatus.CREATED);
    }
    @PutMapping("/updateOnlineApp")
    public ResponseEntity<OnlineAppDto> updateOnlineApp(@RequestParam long id,@RequestBody OnlineAppDto onlineAppDto) throws Exception{
        UserDto userDto=new UserDto();
        userDto.setId(1L);
        System.out.println("ID"+id);
        return new ResponseEntity<>(OnlineAppMapper.mapToDto(onlineAppService.updateOnlineApp(id,onlineAppDto,userDto)), HttpStatus.CREATED);
    }
    @GetMapping("/getOnlineAppById")
    public ResponseEntity<OnlineAppDto> getOnlineAppById(@RequestParam long id)throws Exception{
        return new ResponseEntity<>(OnlineAppMapper.mapToDto(onlineAppService.getOnlineAppById(id)),HttpStatus.OK);
    }
    @GetMapping("/getOnlineAppByOwnerId")
    public ResponseEntity<OnlineAppDto> getOnlineAppByOwnerId(@RequestParam Long ownerId) throws Exception{
        UserDto userDto=new UserDto();
        userDto.setId(1L);
        return new ResponseEntity<>(OnlineAppMapper.mapToDto(onlineAppService.getOnlineAppByOwnerId(userDto.getId())),HttpStatus.OK);
    }
    @GetMapping("/getAllOnlineApps")
    public ResponseEntity<List<OnlineAppDto>> getAllOnlineApps(){
        return new ResponseEntity<>(onlineAppService.getAllOnlineApp().stream().map(OnlineAppMapper::mapToDto).toList(),HttpStatus.OK);
    }
    @GetMapping("/searchOnlineShop")
    public ResponseEntity<List<OnlineAppDto>> searchOnlineApp(@RequestParam String city){
        return new ResponseEntity<>(onlineAppService.searchOnlineApp(city).stream().map(OnlineAppMapper::mapToDto).toList(),HttpStatus.OK);
    }

}
