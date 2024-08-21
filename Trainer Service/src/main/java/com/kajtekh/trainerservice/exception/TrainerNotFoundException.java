package com.kajtekh.trainerservice.exception;

public class TrainerNotFoundException extends RuntimeException {
    public TrainerNotFoundException(Long id) {
        super("Trainer not found with id: " + id);
    }
}
