package com.kajtekh.trainerservice.service;

import com.kajtekh.trainerservice.exception.TrainerNotFoundException;
import com.kajtekh.trainerservice.model.Trainer;
import com.kajtekh.trainerservice.model.dto.CreateTrainerDTO;
import com.kajtekh.trainerservice.model.dto.PreviewTrainerDTO;
import com.kajtekh.trainerservice.model.dto.TrainerDTO;
import com.kajtekh.trainerservice.repository.TrainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;

    TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Transactional(readOnly = true)
    public TrainerDTO getTrainerByUserId(Long userId) {
        return TrainerMapper.toTrainerDTO(trainerRepository.findByUserId(userId));
    }

    @Transactional(readOnly = true)
    public List<Trainer> getTrainerByFirstName(String firstName) {
        return trainerRepository.findByFirstName(firstName);
    }

    @Transactional(readOnly = true)
    public List<PreviewTrainerDTO> getAllTrainers() {
        return trainerRepository.getAllTrainers().stream()
                .map(TrainerMapper::toPreviewTrainerDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<PreviewTrainerDTO> getTrainersPerPage(Long page, Long size) {
        return trainerRepository.getAllTrainers().stream()
                .map(TrainerMapper::toPreviewTrainerDTO)
                .limit(page * size)
                .toList();
    }

    @Transactional
    public Trainer saveTrainer(CreateTrainerDTO trainerDTO) {
        return trainerRepository.save(TrainerMapper.toTrainer(trainerDTO));
    }

    @Transactional
    public Trainer updateTrainer(Long id, TrainerDTO trainerDTO) {
        Trainer trainerToUpdate = trainerRepository.findById(id)
                .orElseThrow(() -> new TrainerNotFoundException(id));
        trainerToUpdate.setFirstName(trainerDTO.firstName());
        trainerToUpdate.setLastName(trainerDTO.lastName());
        trainerToUpdate.setBio(trainerDTO.bio().orElse(null));
        trainerToUpdate.setProfilePictureUrl(trainerDTO.profilePictureUrl().orElse(null));
        return trainerRepository.save(trainerToUpdate);
    }

    @Transactional
    public void deleteTrainer(Long id) {
        trainerRepository.deleteById(id);
    }

}
