package com.jortega.levelup.routines.infrastructure.web;

import com.jortega.levelup.routines.port.in.CreateRoutineUseCase.CreateRoutineCommand;
import jakarta.validation.constraints.*;

import java.util.*;
import java.util.UUID;

public record CreateRoutineRequest(
        @NotBlank String name,
        @Size(min = 1) List<Day> days
) {
    public static record Day(@Min(1) @Max(7) int dayIndex, String name, @Size(min = 1) List<Ex> exercises) {}
    public static record Ex(@NotNull UUID exerciseId,
                            @Min(1) @Max(10) int targetSets,
                            @Min(1) int targetRepsMin,
                            @Min(1) int targetRepsMax) {}


    public CreateRoutineCommand toCommand(UUID ownerUserId) {
        List<CreateRoutineCommand.Day> dayCmds = new ArrayList<>();
        for (Day d : days) {
            List<CreateRoutineCommand.Ex> exCmds = new ArrayList<>();
            for (Ex e : d.exercises) exCmds.add(new CreateRoutineCommand.Ex(e.exerciseId, e.targetSets, e.targetRepsMin, e.targetRepsMax));
            dayCmds.add(new CreateRoutineCommand.Day(d.dayIndex, d.name, exCmds));
        }
        return new CreateRoutineCommand(ownerUserId, name, dayCmds);
    }
}
