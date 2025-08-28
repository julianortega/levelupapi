package com.jortega.levelup.routines.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public final class RoutineExercise {
    private final UUID id;
    private final UUID exerciseId;
    private int sets;
    private int repsMin;
    private int repsMax;
    private int restSeconds;
    private String notes;

    public RoutineExercise(UUID id, UUID exerciseId, int sets, int repsMin, int repsMax, int restSeconds, String notes) {
        if (exerciseId == null) throw new IllegalArgumentException("exerciseId required");
        if (sets < 1 || sets > 10) throw new IllegalArgumentException("sets 1..10");
        if (repsMin < 1 || repsMin > repsMax) throw new IllegalArgumentException("reps range invalid");
        this.id = id;
        this.exerciseId = exerciseId;
        this.sets = sets;
        this.repsMin = repsMin;
        this.repsMax = repsMax;
        this.restSeconds = restSeconds;
        this.notes = notes;
    }

}
