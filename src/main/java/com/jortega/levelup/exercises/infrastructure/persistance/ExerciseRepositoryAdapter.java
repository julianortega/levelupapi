package com.jortega.levelup.exercises.infrastructure.persistance;

import com.jortega.levelup.exercises.domain.Exercise;
import com.jortega.levelup.exercises.port.out.ExerciseRepository;
import com.jortega.levelup.shared.domain.Equipment;
import com.jortega.levelup.shared.domain.MuscleGroup;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.UUID;

@Component
public class ExerciseRepositoryAdapter implements ExerciseRepository {
    private final JpaExerciseRepository jpa;
    public ExerciseRepositoryAdapter(JpaExerciseRepository jpa) { this.jpa = jpa; }


    @Override public Optional<Exercise> findById(UUID id) { return jpa.findById(id).map(this::toDomain); }


    @Override public Map<UUID, Exercise> findAllByIds(Collection<UUID> ids) {
        if (ids.isEmpty()) return Collections.emptyMap();
        return jpa.findAllById(ids).stream().map(this::toDomain).collect(Collectors.toMap(Exercise::getId, e -> e));
    }


    private Exercise toDomain(ExerciseEntity e) {
        return new Exercise(e.getId(), e.getName(), parseMuscle(e.getPrimaryMuscle()), parseEquipment(e.getEquipment()), e.isUnilateral());
    }


    private MuscleGroup parseMuscle(String s) {
        try { return MuscleGroup.valueOf(s.toUpperCase(java.util.Locale.ROOT)); }
        catch (Exception ex) { return MuscleGroup.OTHER; }
    }

    private Equipment parseEquipment(String s) {
        try { return Equipment.valueOf(s.toUpperCase(java.util.Locale.ROOT)); }
        catch (Exception ex) { return Equipment.OTHER; }
    }
}