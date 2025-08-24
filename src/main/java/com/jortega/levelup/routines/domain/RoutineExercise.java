package com.jortega.levelup.routines.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public final class RoutineExercise {
    private final UUID id;
    private final UUID exerciseId;
    private int targetSets;
    private int targetRepsMin;
    private int targetRepsMax;

    public RoutineExercise(UUID id, UUID exerciseId, int targetSets, int targetRepsMin, int targetRepsMax) {
        if (exerciseId == null) throw new IllegalArgumentException("exerciseId required");
        if (targetSets < 1 || targetSets > 10) throw new IllegalArgumentException("sets 1..10");
        if (targetRepsMin < 1 || targetRepsMin > targetRepsMax) throw new IllegalArgumentException("reps range invalid");
        this.id = id == null ? UUID.randomUUID() : id;
        this.exerciseId = exerciseId;
        this.targetSets = targetSets;
        this.targetRepsMin = targetRepsMin;
        this.targetRepsMax = targetRepsMax;
    }

}
