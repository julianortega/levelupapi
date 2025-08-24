package com.jortega.levelup.routines.infrastructure.config;

import com.jortega.levelup.exercises.port.out.ExerciseRepository;
import com.jortega.levelup.routines.application.CreateRoutineService;
import com.jortega.levelup.routines.application.GetRoutineService;
import com.jortega.levelup.routines.application.ListRoutinesService;
import com.jortega.levelup.routines.port.in.CreateRoutineUseCase;
import com.jortega.levelup.routines.port.in.GetRoutineUseCase;
import com.jortega.levelup.routines.port.in.ListRoutinesUseCase;
import com.jortega.levelup.routines.port.out.RoutineRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutineBeansConfig {
    @Bean
    CreateRoutineUseCase createRoutineUseCase(RoutineRepository routines, ExerciseRepository exercises) {
        return new CreateRoutineService(routines, exercises);
    }
    @Bean
    GetRoutineUseCase getRoutineUseCase(RoutineRepository routines) { return new GetRoutineService(routines); }
    @Bean
    ListRoutinesUseCase listRoutinesUseCase(RoutineRepository routines) { return new ListRoutinesService(routines); }
}
