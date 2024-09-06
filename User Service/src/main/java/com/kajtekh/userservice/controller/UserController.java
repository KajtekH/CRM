package com.kajtekh.userservice.controller;

import com.kajtekh.userservice.model.DTO.UserLoginDTO;
import com.kajtekh.userservice.model.DTO.UserRegistrationDTO;
import com.kajtekh.userservice.model.User;
import com.kajtekh.userservice.service.UserMapper;
import com.kajtekh.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public  UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUnactivatedUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody UserRegistrationDTO userDto) {
        return userService.registerUser(UserMapper.mapToUser(userDto));
    }

    @GetMapping("/{username}")
    public Optional<User> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody UserLoginDTO userDto) {
        return userService.loginUser(UserMapper.mapToUser(userDto));
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
    }
}
