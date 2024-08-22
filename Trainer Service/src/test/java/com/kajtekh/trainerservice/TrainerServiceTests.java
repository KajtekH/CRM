package com.kajtekh.trainerservice;


import com.kajtekh.trainerservice.model.dto.TrainerDTO;
import com.kajtekh.trainerservice.service.TrainerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrainerServiceTests {

    InMemoryTrainerRepository trainerRepository;
    TrainerService trainerService;

    @BeforeEach
    void setUp() {
        trainerRepository = new InMemoryTrainerRepository();
        trainerService = new TrainerService(trainerRepository);
    }

    @AfterEach
    void tearDown() {
        trainerRepository.clear();
    }

    @Test
    void whenNoTrainersReturnEmptyList() {
        List<TrainerDTO> trainerList = trainerService.getAllTrainers(1L,10L);
        assertTrue(trainerList.isEmpty());
    }
}
