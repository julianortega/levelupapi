package com.jortega.levelup.exercises.api.dto;

import com.jortega.levelup.exercises.domain.model.Exercise;

import java.util.UUID;

public record ExerciseDto(
        UUID id,
        String name,
        String primaryMuscle,
        String equipment,
        boolean unilateral
) {
    public static ExerciseDto from(Exercise exercise) {
        return new ExerciseDto(
                exercise.getId(),
                exercise.getName(),
                exercise.getPrimaryMuscle().name(),
                exercise.getEquipment().name(),
                exercise.isUnilateral()
        );
    }
}
