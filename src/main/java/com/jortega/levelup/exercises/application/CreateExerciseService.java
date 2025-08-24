package com.jortega.levelup.exercises.application;

import com.jortega.levelup.exercises.domain.model.Exercise;
import com.jortega.levelup.exercises.domain.service.ExerciseService;
import com.jortega.levelup.exercises.port.in.CreateExerciseUseCase;
import com.jortega.levelup.exercises.port.out.ExerciseRepository;

import java.util.Objects;

public class CreateExerciseService implements CreateExerciseUseCase {
    private final ExerciseRepository exerciseRepository;
    private final ExerciseService exerciseService;
    
    public CreateExerciseService(ExerciseRepository exerciseRepository, ExerciseService exerciseService) {
        this.exerciseRepository = Objects.requireNonNull(exerciseRepository);
        this.exerciseService = Objects.requireNonNull(exerciseService);
    }
    
    @Override
    public Result create(CreateExerciseCommand command) {
        Exercise exercise = exerciseService.createExercise(
            command.name(),
            command.primaryMuscle(),
            command.equipment(),
            command.unilateral()
        );
        
        Exercise savedExercise = exerciseRepository.save(exercise);
        
        return new Result(savedExercise.getId(), savedExercise.getName());
    }
}
