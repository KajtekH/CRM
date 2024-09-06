package com.kajtekh.userservice.service;

import com.kajtekh.userservice.model.DTO.UserLoginDTO;
import com.kajtekh.userservice.model.DTO.UserRegistrationDTO;
import com.kajtekh.userservice.model.User;

import static com.kajtekh.userservice.model.UserRole.USER;

public class UserMapper {

    private UserMapper(){}

    public static User mapToUser(UserRegistrationDTO userRegistrationDTO){
        return new User(
                userRegistrationDTO.username(),
                userRegistrationDTO.email(),
                userRegistrationDTO.password(),
                USER
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
