package com.jortega.levelup.exercises.infrastructure.web;

import com.jortega.levelup.shared.domain.Equipment;
import com.jortega.levelup.shared.domain.MuscleGroup;
import jakarta.validation.constraints.NotNull;

public record CreateExerciseRequest(
    @NotNull String name,
    @NotNull MuscleGroup primaryMuscle,
    @NotNull Equipment equipment,
    boolean unilateral
) {}
