package com.kajtekh.userservice.service;

import com.kajtekh.userservice.model.DTO.UserLoginDTO;
import com.kajtekh.userservice.model.DTO.UserRegistrationDTO;
import com.kajtekh.userservice.model.User;

public class UserMapper {

    private UserMapper(){}

    public static User mapToUser(UserRegistrationDTO userRegistrationDTO){
        return new User(
                userRegistrationDTO.username(),
                userRegistrationDTO.email(),
                userRegistrationDTO.password()
        );
    }

    public static UserRegistrationDTO mapToUserRegistrationDTO(User user){
        return new UserRegistrationDTO(
                user.getUsername(),
                user.getEmail(),
                user.getPassword()
        );
    }

    public static User mapToUser(UserLoginDTO userLoginDTO){
        return new User(
                userLoginDTO.usernameOrEmail(),
                userLoginDTO.password()
        );
    }

}
