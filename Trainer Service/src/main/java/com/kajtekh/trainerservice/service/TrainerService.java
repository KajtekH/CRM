package com.kajtekh.trainerservice.service;

import com.kajtekh.trainerservice.model.Trainer;
import com.kajtekh.trainerservice.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;

    TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }


    public Trainer getTrainerByUserId(Long userId) {
        return trainerRepository.findByUserId(userId);
    }

    public Trainer getTrainerByFirstName(String firstName) {
        return trainerRepository.findByFirstName(firstName);
    }

    public Trainer saveTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    public void deleteTrainer(Long id) {
        trainerRepository.deleteById(id);
    }

    public List<Trainer> getAllTrainers() {
        return trainerRepository.getAllTrainers();
    }



}
