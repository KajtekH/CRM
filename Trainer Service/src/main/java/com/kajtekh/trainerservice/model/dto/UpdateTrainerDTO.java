package com.kajtekh.trainerservice.model.dto;

import java.util.Optional;

public record UpdateTrainerDTO(String firstName,
                               String lastName,
                               Optional<String> bio,
                               Optional<String> profilePictureUrl) {
}
