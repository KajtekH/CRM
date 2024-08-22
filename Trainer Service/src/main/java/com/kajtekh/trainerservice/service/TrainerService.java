package com.kajtekh.trainerservice.service;

import com.kajtekh.trainerservice.exception.TrainerNotFoundException;
import com.kajtekh.trainerservice.model.Trainer;
import com.kajtekh.trainerservice.model.dto.CreateTrainerDTO;
import com.kajtekh.trainerservice.model.dto.PreviewTrainerDTO;
import com.kajtekh.trainerservice.model.dto.TrainerDTO;
import com.kajtekh.trainerservice.model.dto.UpdateTrainerDTO;
import com.kajtekh.trainerservice.repository.TrainerRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TrainerService {

    private final TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Transactional(readOnly = true)
    public TrainerDTO getTrainerById(Long id) {
        return TrainerMapper.toTrainerDTO(trainerRepository.findById(id)
                .orElseThrow(() -> new TrainerNotFoundException(id)));
    }

    @Transactional(readOnly = true)
    public TrainerDTO getTrainerByUserId(Long userId) {
        return TrainerMapper.toTrainerDTO(trainerRepository.findByUserId(userId)
                .orElseThrow(() -> new TrainerNotFoundException(userId)));
    }

    @Transactional(readOnly = true)
    public List<PreviewTrainerDTO> getTrainersByFirstName(String firstName, Long page, Long size) {
        PageRequest pageRequest = PageRequest.of(page.intValue() - 1, size.intValue());
        return trainerRepository.findByFirstName(firstName, pageRequest).stream()
                .map(TrainerMapper::toPreviewTrainerDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<TrainerDTO> getAllTrainers(Long page, Long size) {
        PageRequest pageRequest = PageRequest.of(page.intValue() - 1, size.intValue());
        return trainerRepository.findAll(pageRequest).stream()
                .map(TrainerMapper::toTrainerDTO)
                .toList();
    }

    @Transactional
    public Trainer saveTrainer(CreateTrainerDTO trainerDTO) {
        return trainerRepository.save(TrainerMapper.toTrainer(trainerDTO));
    }

    @Transactional
    public Trainer updateTrainer(Long id, UpdateTrainerDTO trainerDTO) {
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
        Trainer trainerToDelete = trainerRepository.findById(id)
                .orElseThrow(() -> new TrainerNotFoundException(id));
        trainerRepository.delete(trainerToDelete);
    }

}
