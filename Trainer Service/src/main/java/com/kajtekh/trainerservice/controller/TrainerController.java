package com.kajtekh.trainerservice.controller;

import com.kajtekh.trainerservice.model.dto.CreateTrainerDTO;
import com.kajtekh.trainerservice.model.dto.PreviewTrainerDTO;
import com.kajtekh.trainerservice.model.dto.TrainerDTO;
import com.kajtekh.trainerservice.model.dto.UpdateTrainerDTO;
import com.kajtekh.trainerservice.service.TrainerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/trainers")
public class TrainerController {

    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping()
    public ResponseEntity<List<TrainerDTO>> getAllTrainers() {
        return ResponseEntity.ok(trainerService.getAllTrainers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainerDTO> getTrainerById(@PathVariable Long id) {
        return ResponseEntity.ok(trainerService.getTrainerById(id));
    }

    @GetMapping("/name/{firstName}")
    public ResponseEntity<List<PreviewTrainerDTO>> getTrainersByFirstName(@PathVariable String firstName) {
        return ResponseEntity.ok(trainerService.getTrainersByFirstName(firstName));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void createTrainer(@RequestBody CreateTrainerDTO trainerDTO) {
        trainerService.saveTrainer(trainerDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainerDTO> updateTrainer(@PathVariable Long id, @RequestBody UpdateTrainerDTO trainerDTO) {
        trainerService.updateTrainer(id, trainerDTO);
        return ResponseEntity.ok(trainerService.getTrainerById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTrainer(@PathVariable Long id) {
        trainerService.deleteTrainer(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
