package com.kajtekh.trainerservice;


import com.kajtekh.trainerservice.exception.TrainerNotFoundException;
import com.kajtekh.trainerservice.model.Trainer;
import com.kajtekh.trainerservice.model.dto.CreateTrainerDTO;
import com.kajtekh.trainerservice.model.dto.TrainerDTO;
import com.kajtekh.trainerservice.model.dto.UpdateTrainerDTO;
import com.kajtekh.trainerservice.service.TrainerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TrainerServiceTests {

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

    @Test
    void whenTrainerDoesNotExistShouldThrowException() {
        assertThrows(TrainerNotFoundException.class, () -> trainerService.getTrainerById(-1L));
    }

    @Test
    void whenUpdatedTrainerDoesNotExistShouldThrowException() {
        UpdateTrainerDTO updateTrainer = new UpdateTrainerDTO(
                "TestFirstName",
                "TestLastName",
                Optional.of("TestBio"),
                Optional.of("TestProfilePictureUrl")
        );
        assertThrows(TrainerNotFoundException.class, () -> trainerService.updateTrainer(-1L, updateTrainer));
    }

    @Test
    void shouldCreateTrainer() {
        CreateTrainerDTO createTrainer = createTrainer();
        trainerService.saveTrainer(createTrainer);
        PageRequest pageRequest = createPageRequest();
        assertEquals(1L, trainerRepository.findByFirstName("TestFirstName", pageRequest).size());
    }

    @Test
    void getTrainerById_ShouldReturnTrainer_WhenTrainerExists() {
        CreateTrainerDTO createTrainer = createTrainer();
        Trainer savedTrainer = trainerService.saveTrainer(createTrainer);
        TrainerDTO foundTrainer = trainerService.getTrainerById(savedTrainer.getId());
        assertEquals(savedTrainer.getFirstName(), foundTrainer.firstName());
        assertEquals(savedTrainer.getLastName(), foundTrainer.lastName());
        assertEquals(savedTrainer.getBio(), foundTrainer.bio().orElse(null));
        assertEquals(savedTrainer.getProfilePictureUrl(), foundTrainer.profilePictureUrl().orElse(null));
    }

    private CreateTrainerDTO createTrainer() {
        return new CreateTrainerDTO(
                100L,
                "TestFirstName",
                "TestLastName",
                Optional.of("TestBio"),
                Optional.empty()
        );
    }

    private PageRequest createPageRequest() {
        return PageRequest.of(1, 10);
    }
}
