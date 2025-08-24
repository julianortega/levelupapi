package com.jortega.levelup.routines.domain;

import java.util.UUID;

public final class RoutineExercise {
    private final UUID id;
    private final UUID exerciseId;
    private int targetSets;
    private int targetRepsMin;
    private int targetRepsMax;
    private String notes;


    public RoutineExercise(UUID id, UUID exerciseId, int targetSets, int targetRepsMin, int targetRepsMax, String notes) {
        if (exerciseId == null) throw new IllegalArgumentException("exerciseId required");
        if (targetSets < 1 || targetSets > 10) throw new IllegalArgumentException("sets 1..10");
        if (targetRepsMin < 1 || targetRepsMin > targetRepsMax) throw new IllegalArgumentException("reps range invalid");
        this.id = id == null ? UUID.randomUUID() : id;
        this.exerciseId = exerciseId;
        this.targetSets = targetSets;
        this.targetRepsMin = targetRepsMin;
        this.targetRepsMax = targetRepsMax;
        this.notes = notes;
    }


    public UUID getId() { return id; }
    public UUID getExerciseId() { return exerciseId; }
    public int getTargetSets() { return targetSets; }
    public int getTargetRepsMin() { return targetRepsMin; }
    public int getTargetRepsMax() { return targetRepsMax; }
    public String getNotes() { return notes; }
}
