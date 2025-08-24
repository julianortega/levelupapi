package com.jortega.levelup.exercises.infrastructure.persistance;

import com.jortega.levelup.exercises.domain.model.Exercise;
import com.jortega.levelup.exercises.port.out.ExerciseRepository;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ExerciseRepositoryAdapter implements ExerciseRepository {
    private final JpaExerciseRepository jpa;
    public ExerciseRepositoryAdapter(JpaExerciseRepository jpa) { this.jpa = jpa; }


    @Override public Optional<Exercise> findById(UUID id) { return jpa.findById(id).map(this::toDomain); }


    @Override public Map<UUID, Exercise> findAllByIds(Collection<UUID> ids) {
        if (ids.isEmpty()) return Collections.emptyMap();
        return jpa.findAllById(ids).stream().map(this::toDomain).collect(Collectors.toMap(Exercise::getId, e -> e));
    }

    @Override public Exercise save(Exercise exercise) {
        ExerciseEntity entity = toEntity(exercise);
        ExerciseEntity savedEntity = jpa.save(entity);
        return toDomain(savedEntity);
    }

    private Exercise toDomain(ExerciseEntity e) {
        return new Exercise(e.getId(), e.getName(), e.getPrimaryMuscle(), e.getEquipment(), e.isUnilateral());
    }

    private ExerciseEntity toEntity(Exercise exercise) {
        ExerciseEntity entity = new ExerciseEntity();
        entity.setId(exercise.getId());
        entity.setName(exercise.getName());
        entity.setPrimaryMuscle(exercise.getPrimaryMuscle());
        entity.setEquipment(exercise.getEquipment());
        entity.setUnilateral(exercise.isUnilateral());
        return entity;
    }
}