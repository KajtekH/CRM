package com.kajtekh.userservice.controller;

import com.kajtekh.userservice.model.DTO.UserLoginDTO;
import com.kajtekh.userservice.security.JwtAuthenticationResponse;
import com.kajtekh.userservice.security.JwtTokenProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public JwtAuthenticationResponse authenticateUser(@Valid @RequestBody UserLoginDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.usernameOrEmail(),
                        loginRequest.password()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication.getName());
        return new JwtAuthenticationResponse(jwt);
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        jwtTokenProvider.validateToken(token);
        return "Token is valid";
    }
}