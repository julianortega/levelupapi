package com.jortega.levelup.exercises.port.in;

import com.jortega.levelup.exercises.domain.model.Exercise;

import java.util.UUID;

public interface GetExerciseUseCase {
    Result get(GetExerciseCommand command);
    
    record GetExerciseCommand(UUID exerciseId) {
        public GetExerciseCommand {
            if (exerciseId == null) {
                throw new IllegalArgumentException("Exercise ID is required");
            }
        }
    }
    
    record Result(Exercise exercise) {
        public Result {
            if (exercise == null) {
                throw new IllegalArgumentException("Exercise cannot be null");
            }
        }
    }
}
