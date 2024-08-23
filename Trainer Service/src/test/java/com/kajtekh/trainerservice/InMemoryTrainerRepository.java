package com.kajtekh.trainerservice;

import com.kajtekh.trainerservice.model.Trainer;
import com.kajtekh.trainerservice.repository.TrainerRepository;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryTrainerRepository implements TrainerRepository {

    private final Map<Long, Trainer> trainers = new HashMap<>();
    private Long mapKey = 1L;

    public void clear(){
        trainers.clear();
        mapKey = 1L;
    }

    @Override
    public Optional<Trainer> findByUserId(Long userId) {
        return Optional.ofNullable(trainers.values().stream()
                .filter(trainer -> trainer.getUserId().equals(userId))
                .findFirst()
                .orElse(null));
    }

    @Override
    public List<Trainer> findByFirstName(String firstName, Pageable pageable) {
        return trainers.values().stream()
                .filter(trainer -> trainer.getFirstName().equals(firstName))
                .toList();
    }

    @Override
    public List<Trainer> findAll(Pageable pageable) {
        return trainers.values().stream()
                .toList();
    }

    @Override
    public <S extends Trainer> S save(S trainer) {
        trainer.setId(mapKey++);
        trainers.put(trainer.getId(),trainer);
        return trainer;
    }

    @Override
    public Optional<Trainer> findById(Long id) {
        return Optional.ofNullable(trainers.get(id));
    }

    @Override
    public void delete(Trainer entity) {
        trainers.remove(entity.getId());
    }


    @Override
    public <S extends Trainer> Iterable<S> saveAll(Iterable<S> entities) {  // Non implemented - method is empty
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {  // Non implemented - method is empty
        return false;
    }

    @Override
    public Iterable<Trainer> findAll() {  // Non implemented - method is empty
        return null;
    }

    @Override
    public Iterable<Trainer> findAllById(Iterable<Long> longs) {  // Non implemented - method is empty
        return null;
    }

    @Override
    public long count() {  // Non implemented - method is empty
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {  // Non implemented - method is empty

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {  // Non implemented - method is empty

    }

    @Override
    public void deleteAll(Iterable<? extends Trainer> entities) {  // Non implemented - method is empty

    }

    @Override
    public void deleteAll() {  // Non implemented - method is empty

    }
}
