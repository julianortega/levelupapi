package com.jortega.levelup.routines.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public final class RoutineDay {
    private final UUID id;
    private final int dayIndex;
    private final String name;
    private final List<RoutineExercise> exercises = new ArrayList<>();


    public RoutineDay(UUID id, int dayIndex, String name) {
        if (dayIndex < 1 || dayIndex > 7) throw new IllegalArgumentException("dayIndex must be 1..7");
        this.id = id == null ? UUID.randomUUID() : id;
        this.dayIndex = dayIndex;
        this.name = name;
    }


    public UUID getId() { return id; }
    public int getDayIndex() { return dayIndex; }
    public String getName() { return name; }
    public List<RoutineExercise> getExercises() { return Collections.unmodifiableList(exercises); }


    public RoutineExercise addExercise(UUID exerciseId, int targetSets, int repsMin, int repsMax) {
        if (exerciseId == null) throw new IllegalArgumentException("exerciseId required");
        if (targetSets < 1 || targetSets > 10) throw new IllegalArgumentException("targetSets 1..10");
        if (repsMin < 1 || repsMin > repsMax) throw new IllegalArgumentException("reps range invalid");
        RoutineExercise re = new RoutineExercise(UUID.randomUUID(), exerciseId, targetSets, repsMin, repsMax);
        this.exercises.add(re);
        return re;
    }
}
