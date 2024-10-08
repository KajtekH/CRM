package com.kajtekh.trainerservice.service;

import com.kajtekh.trainerservice.model.Trainer;
import com.kajtekh.trainerservice.model.dto.CreateTrainerDTO;
import com.kajtekh.trainerservice.model.dto.PreviewTrainerDTO;
import com.kajtekh.trainerservice.model.dto.TrainerDTO;
import com.kajtekh.trainerservice.model.dto.UpdateTrainerDTO;

import java.util.Optional;

public class TrainerMapper {

    private TrainerMapper() {}

    public static TrainerDTO toTrainerDTO(Trainer trainer) {
        return new TrainerDTO(trainer.getId(),
                trainer.getUserId(),
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

    public static Trainer toTrainer(UpdateTrainerDTO updateTrainerDTO) {
        return new Trainer(updateTrainerDTO.firstName(),
                updateTrainerDTO.lastName(),
                updateTrainerDTO.bio().orElse(null),
                updateTrainerDTO.profilePictureUrl().orElse(null));
    }

    public static Trainer toTrainer(CreateTrainerDTO createTrainerDTO) {
        return new Trainer(createTrainerDTO.userId(),
                createTrainerDTO.firstName(),
                createTrainerDTO.lastName(),
                createTrainerDTO.bio().orElse(null),
                createTrainerDTO.profilePictureUrl().orElse(null));
    }

    public static PreviewTrainerDTO toPreviewTrainerDTO(Trainer trainer) {
        return new PreviewTrainerDTO(trainer.getFirstName(), trainer.getLastName());
    }
}
