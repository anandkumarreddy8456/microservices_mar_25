package com.anand.microservice.userService.service;

import com.anand.microservice.userService.dto.User;
import com.anand.microservice.userService.exception.UserException;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUser(long id) throws UserException;
    List<User> getAllUsers();
    void deleteUser(long id) throws UserException;
    User updateUser(long id, User user) throws UserException;

}
