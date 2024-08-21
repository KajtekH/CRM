package com.kajtekh.trainerservice.model.dto;

import java.util.Optional;

public record TrainerDTO(Long id,
                         Long userId,
                         String firstName,
                         String lastName,
                         Optional<String> bio,
                         Optional<String> profilePictureUrl) {
}
