package com.kajtekh.trainerservice.repository;

import com.kajtekh.trainerservice.model.Trainer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TrainerRepository extends CrudRepository<Trainer, Long> {

    Optional<Trainer> findByUserId(Long userId);
    List<Trainer> findByFirstName(String firstName);

    List<Trainer> findAll();
}
