package com.jortega.levelup.exercises.port.out;

import com.jortega.levelup.exercises.domain.model.Exercise;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface ExerciseRepository {
    Optional<Exercise> findById(UUID id);
    Map<UUID, Exercise> findAllByIds(Collection<UUID> ids);
    Exercise save(Exercise exercise);
}
