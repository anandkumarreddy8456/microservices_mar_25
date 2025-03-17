package com.anand.microservice.userService.serviceimpl;

import com.anand.microservice.userService.dto.User;
import com.anand.microservice.userService.exception.UserException;
import com.anand.microservice.userService.repository.UserRepository;
import com.anand.microservice.userService.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(long id) throws UserException {
        Optional<User> opt=userRepository.findById(id);
        if(opt.isPresent()){
            return opt.get();
        }
         throw new UserException("user not found");
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(long id) throws UserException{
        Optional<User> opt=userRepository.findById(id);
        if(opt.isEmpty()){
            throw new UserException("user not Found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(long id, User user) throws UserException {
        Optional<User> opt=userRepository.findById(id);
        if(opt.isEmpty()){
            throw new UserException("User not found ");
        }
        User existing =opt.get();
        existing.setEmail(user.getEmail());
        existing.setFullName(user.getFullName());
        existing.setPhone(user.getPhone());
        existing.setUsername(user.getUsername());
        return existing;
    }
}
