package com.anand.microservice.userService.controller;

import com.anand.microservice.userService.dto.User;
import com.anand.microservice.userService.exception.UserException;
import com.anand.microservice.userService.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/userService")
public class UserController {

    private final UserService userService;
    @PostMapping("/" +
            "")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user){
        User createdUser=userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }
    @GetMapping("/getById")
    public ResponseEntity<User> geUserById(@RequestParam long id) throws UserException {
        return new ResponseEntity<>(userService.getUser(id),HttpStatus.OK);
    }
    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestParam long id,@RequestBody User user) throws UserException{
        return new ResponseEntity<>(userService.updateUser(id,user),HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUserById(@RequestParam long id) throws UserException{
        userService.deleteUser(id);
        return new ResponseEntity<>("User deleted SuccessFully",HttpStatus.OK);
    }

}
