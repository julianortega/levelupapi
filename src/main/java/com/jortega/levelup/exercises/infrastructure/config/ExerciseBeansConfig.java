package com.jortega.levelup.exercises.infrastructure.config;

import com.jortega.levelup.exercises.application.ListExercisesService;
import com.jortega.levelup.exercises.port.in.ListExercisesUseCase;
import com.jortega.levelup.exercises.port.out.ExerciseQueryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExerciseBeansConfig {
    @Bean
    ListExercisesUseCase listExercisesUseCase(ExerciseQueryRepository queries) {
        return new ListExercisesService(queries);
    }
}