package com.kajtekh.trainerservice.service;

import com.kajtekh.trainerservice.model.Trainer;
import com.kajtekh.trainerservice.model.dto.TrainerDTO;

import java.util.Optional;

public class TrainerMapper {

    private TrainerMapper() {};

    public static TrainerDTO toTrainerDTO(Trainer trainer) {
        return new TrainerDTO(trainer.getUserId(),
                trainer.getFirstName(),
                trainer.getLastName(),
                Optional.ofNullable(trainer.getBio()),
                Optional.ofNullable(trainer.getProfilePictureUrl()));
    }

    public static Trainer toTrainer(TrainerDTO trainerDTO) {
        return new Trainer(trainerDTO.userId(),
                trainerDTO.firstName(),
                trainerDTO.lastName(),
                trainerDTO.bio().orElse(null),
                trainerDTO.profilePictureUrl().orElse(null));
    }
}
