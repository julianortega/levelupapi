package com.jortega.levelup.exercises.infrastructure.web;

import com.jortega.levelup.exercises.domain.model.Exercise;

import java.util.UUID;


public record ExerciseResponse(UUID id, String name, String primaryMuscle, String equipment, boolean unilateral) {
    public static ExerciseResponse from(Exercise e) {
        return new ExerciseResponse(e.getId(), e.getName(), e.getPrimaryMuscle().name(), e.getEquipment().name(), e.isUnilateral());
    }

}
