package com.jortega.levelup.routines.port.in;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public interface CreateRoutineUseCase {
    UUID create(CreateRoutineCommand command);

    final class CreateRoutineCommand {
        public final UUID ownerUserId;
        public final String name;
        public final List<Day> days;
        public CreateRoutineCommand(UUID ownerUserId, String name, List<Day> days) {
            this.ownerUserId = Objects.requireNonNull(ownerUserId);
            if (name == null || name.isBlank()) throw new IllegalArgumentException("name required");
            if (days == null || days.isEmpty()) throw new IllegalArgumentException("days required");
            this.name = name; this.days = days;
        }
        public static final class Day {
            public final int dayIndex; public final String name; public final List<Ex> exercises;
            public Day(int dayIndex, String name, List<Ex> exercises) {
                this.dayIndex = dayIndex; this.name = name; this.exercises = Objects.requireNonNull(exercises);
            }
        }
        public static final class Ex {
            public final UUID exerciseId; public final int sets; public final int repsMin; public final int repsMax;
            public Ex(UUID exerciseId, int sets, int repsMin, int repsMax) {
                this.exerciseId = Objects.requireNonNull(exerciseId);
                this.sets = sets; this.repsMin = repsMin; this.repsMax = repsMax;
            }
        }
    }
}
