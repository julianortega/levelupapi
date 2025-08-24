package com.jortega.levelup.exercises.application;

import com.jortega.levelup.exercises.domain.model.Exercise;
import com.jortega.levelup.exercises.port.in.GetExerciseUseCase;
import com.jortega.levelup.exercises.port.out.ExerciseRepository;
import com.jortega.levelup.shared.domain.exception.ResourceNotFoundException;

import java.util.Objects;

public class GetExerciseService implements GetExerciseUseCase {
    private final ExerciseRepository exerciseRepository;
    
    public GetExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = Objects.requireNonNull(exerciseRepository);
    }
    
    @Override
    public Result get(GetExerciseCommand command) {
        Exercise exercise = exerciseRepository.findById(command.exerciseId())
            .orElseThrow(() -> new ResourceNotFoundException("Exercise", command.exerciseId().toString()));
        
        return new Result(exercise);
    }
}
