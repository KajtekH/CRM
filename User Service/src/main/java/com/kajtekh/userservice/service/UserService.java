package com.kajtekh.userservice.service;


import com.kajtekh.userservice.exception.EmailException;
import com.kajtekh.userservice.kafka.KafkaProducer;
import com.kajtekh.userservice.model.User;
import com.kajtekh.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final KafkaProducer kafkaProducer;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, KafkaProducer kafkaProducer) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.kafkaProducer = kafkaProducer;
    }

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(false);
        if(Boolean.TRUE.equals(userExists(user))){
            throw new EmailException(user.getEmail());
        }

        kafkaProducer.sendMessage(user.getEmail());
        return userRepository.save(user);

    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User loginUser(User user) {
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
        if(userOptional.isPresent()){
            User userFromDb = userOptional.get();
            if(passwordEncoder.matches(user.getPassword(), userFromDb.getPassword())){
                return userFromDb;
            }
        }
        return null;
    }

    private Boolean userExists(User user){
        return userRepository.findByEmail(user.getEmail()).isPresent();
    }

}