package com.jortega.levelup.exercises.port.in;

import com.jortega.levelup.shared.domain.Equipment;
import com.jortega.levelup.shared.domain.MuscleGroup;

import java.util.UUID;

public interface CreateExerciseUseCase {
    Result create(CreateExerciseCommand command);
    
    record CreateExerciseCommand(String name, MuscleGroup primaryMuscle, Equipment equipment, boolean unilateral) {
        public CreateExerciseCommand {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Exercise name is required");
            }
            if (primaryMuscle == null) {
                throw new IllegalArgumentException("Primary muscle group is required");
            }
            if (equipment == null) {
                throw new IllegalArgumentException("Equipment is required");
            }
        }
    }
    
    record Result(UUID exerciseId, String name) {
        public Result {
            if (exerciseId == null) {
                throw new IllegalArgumentException("Exercise ID cannot be null");
            }
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Exercise name cannot be null or empty");
            }
        }
    }
}
