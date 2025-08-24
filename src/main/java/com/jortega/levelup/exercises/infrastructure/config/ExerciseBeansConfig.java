package com.jortega.levelup.exercises.infrastructure.config;

import com.jortega.levelup.exercises.application.CreateExerciseService;
import com.jortega.levelup.exercises.application.GetExerciseService;
import com.jortega.levelup.exercises.application.ListExercisesService;
import com.jortega.levelup.exercises.domain.service.ExerciseService;
import com.jortega.levelup.exercises.port.in.CreateExerciseUseCase;
import com.jortega.levelup.exercises.port.in.GetExerciseUseCase;
import com.jortega.levelup.exercises.port.in.ListExercisesUseCase;
import com.jortega.levelup.exercises.port.out.ExerciseQueryRepository;
import com.jortega.levelup.exercises.port.out.ExerciseRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExerciseBeansConfig {
    
    @Bean
    ExerciseService exerciseService() {
        return new ExerciseService();
    }
    
    @Bean
    ListExercisesUseCase listExercisesUseCase(ExerciseQueryRepository queries) {
        return new ListExercisesService(queries);
    }
    
    @Bean
    CreateExerciseUseCase createExerciseUseCase(ExerciseRepository repository, ExerciseService exerciseService) {
        return new CreateExerciseService(repository, exerciseService);
    }
    
    @Bean
    GetExerciseUseCase getExerciseUseCase(ExerciseRepository repository) {
        return new GetExerciseService(repository);
    }
}