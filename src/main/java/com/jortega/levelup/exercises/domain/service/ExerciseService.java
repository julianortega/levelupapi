package com.jortega.levelup.exercises.domain.service;

import com.jortega.levelup.exercises.domain.model.Exercise;
import com.jortega.levelup.shared.domain.Equipment;
import com.jortega.levelup.shared.domain.MuscleGroup;
import com.jortega.levelup.shared.domain.exception.ValidationException;

import java.util.UUID;

public class ExerciseService {
    
    public Exercise createExercise(String name, MuscleGroup primaryMuscle, Equipment equipment, boolean unilateral) {
        validateExerciseName(name);
        return new Exercise(null, name, primaryMuscle, equipment, unilateral);
    }
    
    public Exercise createExerciseWithId(UUID id, String name, MuscleGroup primaryMuscle, Equipment equipment, boolean unilateral) {
        validateExerciseName(name);
        return new Exercise(id, name, primaryMuscle, equipment, unilateral);
    }
    
    private void validateExerciseName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("name", "Exercise name cannot be null or empty");
        }
        if (name.length() > 100) {
            throw new ValidationException("name", "Exercise name cannot exceed 100 characters");
        }
    }
    
    public boolean isValidForRoutine(Exercise exercise, MuscleGroup targetMuscleGroup) {
        return exercise.getPrimaryMuscle() == targetMuscleGroup || 
               exercise.getPrimaryMuscle() == MuscleGroup.OTHER;
    }
}
