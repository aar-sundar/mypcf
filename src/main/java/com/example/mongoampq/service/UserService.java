package com.example.mongoampq.service;

import com.example.mongoampq.exception.AlreadyExistException;
import com.example.mongoampq.exception.BadRequestException;
import com.example.mongoampq.exception.NotFoundException;
import com.example.mongoampq.model.User;
import com.example.mongoampq.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void createUser(User user){
        LOGGER.debug("User to be created={}", user);
        if(!isUserExist(user)){
            userRepository.save(user);
        }
    }

    public User findUserByEmail(String email){
        if(email == null){
            LOGGER.error("Email is invalid={}", email);
            throw new BadRequestException("Email is invalid="+email);
        }
        User user = userRepository.getByEmail(email);
        if(user == null){
            LOGGER.error("User not found for email={}", email);
            throw new NotFoundException("User not found for the email="+email);
        }

        return user;
    }

    public User findUserByPhone(String mobile){
        if(mobile == null){
            LOGGER.error("Mobile is invalid={}", mobile);
            throw new BadRequestException("Mobile is invalid="+mobile);
        }
        User user = userRepository.getByMobile(mobile);
        if(user == null){
            LOGGER.error("User not found for mobile={}", mobile);
            throw new NotFoundException("User not found for the mobile="+mobile);
        }

        return user;
    }
    private boolean isUserExist(User user){
        if(userRepository.getByEmail(user.getEmail())!=null){
            LOGGER.error("User Already Exist, Email={}",user.getEmail());
            throw new AlreadyExistException("User Already Exist="+user.getEmail());
        }
        return false;
    }
}
