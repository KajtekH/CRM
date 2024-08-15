package com.kajtekh.userservice.controller;

import com.kajtekh.userservice.model.DTO.UserRegistrationDTO;
import com.kajtekh.userservice.model.User;
import com.kajtekh.userservice.service.UserMapper;
import com.kajtekh.userservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public  UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody UserRegistrationDTO userDto) {
        return userService.registerUser(UserMapper.mapToUser(userDto));
    }

    @GetMapping("/{username}")
    public Optional<User> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }
}
