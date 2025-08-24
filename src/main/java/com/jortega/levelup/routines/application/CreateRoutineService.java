package com.jortega.levelup.routines.application;

import com.jortega.levelup.exercises.port.out.ExerciseRepository;
import com.jortega.levelup.routines.domain.Routine;
import com.jortega.levelup.routines.domain.RoutineDay;
import com.jortega.levelup.routines.port.in.CreateRoutineUseCase;
import com.jortega.levelup.routines.port.out.RoutineRepository;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class CreateRoutineService implements CreateRoutineUseCase {
    private final RoutineRepository routines;
    private final ExerciseRepository exercises;


    public CreateRoutineService(RoutineRepository routines, ExerciseRepository exercises) {
        this.routines = routines; this.exercises = exercises;
    }


    @Override
    public UUID create(CreateRoutineCommand cmd) {
        Set<UUID> ids = new HashSet<>();
        for (var d : cmd.days) for (var ex : d.exercises) ids.add(ex.exerciseId);
        Map<UUID, ?> found = exercises.findAllByIds(ids);
        if (found.size() != ids.size()) throw new IllegalArgumentException("Some exerciseIds do not exist");


        Routine r = new Routine(null, cmd.ownerUserId, cmd.name);
        for (var day : cmd.days) {
            RoutineDay d = r.addDay(day.dayIndex, day.name);
            for (var ex : day.exercises) {
                d.addExercise(ex.exerciseId, ex.sets, ex.repsMin, ex.repsMax);
            }
        }
        return routines.save(r).getId();
    }
}
