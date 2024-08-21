package com.kajtekh.userservice.exception;

public class EmailException extends RuntimeException{
    public EmailException(String email){
        super("User with " + email + " already exists");
    }
}
