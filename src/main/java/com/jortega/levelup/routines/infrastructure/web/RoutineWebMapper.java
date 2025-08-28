package com.jortega.levelup.routines.infrastructure.web;

import com.jortega.levelup.routines.domain.Routine;
import com.jortega.levelup.routines.domain.RoutineDay;
import com.jortega.levelup.routines.domain.RoutineExercise;

import java.util.ArrayList;
import java.util.List;

public final class RoutineWebMapper {
    private RoutineWebMapper() {}

    public static RoutineResponse toResponse(Routine r) {
        List<RoutineResponse.Day> days = new ArrayList<>();
        for (RoutineDay d : r.getDays()) {
            List<RoutineResponse.Ex> exs = new ArrayList<>();
            for (RoutineExercise rx : d.getExercises()) {
                exs.add(new RoutineResponse.Ex(rx.getId(), rx.getExerciseId(), rx.getSets(), rx.getRepsMin(), rx.getRepsMax()));
            }
            days.add(new RoutineResponse.Day(d.getId(), d.getDayIndex(), d.getName(), exs));
        }
        return new RoutineResponse(r.getId(), r.getName(), r.getOwnerUserId(), days);
    }

    public static RoutineSummary toSummary(Routine r) {
        return new RoutineSummary(r.getId(), r.getName());
    }
}
