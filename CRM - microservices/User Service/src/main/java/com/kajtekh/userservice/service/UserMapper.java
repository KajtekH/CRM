package com.kajtekh.userservice.service;

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

}
