package com.kajtekh.trainerservice.model.dto;

import java.util.Optional;

public record CreateTrainerDTO(Long userId,
                               String firstName,
                               String lastName,
                               Optional<String> bio,
                               Optional<String> profilePictureUrl) {
}
