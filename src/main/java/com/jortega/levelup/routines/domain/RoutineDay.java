package com.jortega.levelup.routines.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
public final class RoutineDay {
    private final UUID id;
    private final int dayIndex;
    private final String name;
    private final List<RoutineExercise> exercises = new ArrayList<>();


    public RoutineDay(UUID id, int dayIndex, String name) {
        if (dayIndex < 1 || dayIndex > 7) throw new IllegalArgumentException("dayIndex must be 1..7");
        this.id = id;
        this.dayIndex = dayIndex;
        this.name = name;
    }

    public List<RoutineExercise> getExercises() { return Collections.unmodifiableList(exercises); }


    public RoutineExercise addExercise(UUID exerciseId, int sets, int repsMin, int repsMax, int restSeconds, String notes) {
        if (exerciseId == null) throw new IllegalArgumentException("exerciseId required");
        if (sets < 1 || sets > 10) throw new IllegalArgumentException("sets 1..10");
        if (repsMin < 1 || repsMin > repsMax) throw new IllegalArgumentException("reps range invalid");
        RoutineExercise re = new RoutineExercise(UUID.randomUUID(), exerciseId, sets, repsMin, repsMax, restSeconds, notes);
        this.exercises.add(re);
        return re;
    }
}
