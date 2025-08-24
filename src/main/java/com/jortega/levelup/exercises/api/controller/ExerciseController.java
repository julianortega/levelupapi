package com.jortega.levelup.exercises.api.controller;

import com.jortega.levelup.exercises.api.dto.ExerciseDto;
import com.jortega.levelup.exercises.api.dto.FilterOptionsResponse;
import com.jortega.levelup.exercises.domain.model.Exercise;
import com.jortega.levelup.exercises.domain.ListExercisesQuery;
import com.jortega.levelup.exercises.api.dto.PageResult;
import com.jortega.levelup.exercises.infrastructure.web.CreateExerciseRequest;
import com.jortega.levelup.exercises.infrastructure.web.ExerciseResponse;
import com.jortega.levelup.exercises.port.in.CreateExerciseUseCase;
import com.jortega.levelup.exercises.port.in.GetExerciseUseCase;
import com.jortega.levelup.exercises.port.in.ListExercisesUseCase;
import com.jortega.levelup.shared.domain.Equipment;
import com.jortega.levelup.shared.domain.MuscleGroup;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
@Validated
@RequestMapping("/api/exercises")
public class ExerciseController {
    private final ListExercisesUseCase listExercises;
    private final CreateExerciseUseCase createExercise;
    private final GetExerciseUseCase getExercise;
    
    public ExerciseController(ListExercisesUseCase listExercises, 
                            CreateExerciseUseCase createExercise,
                            GetExerciseUseCase getExercise) { 
        this.listExercises = listExercises;
        this.createExercise = createExercise;
        this.getExercise = getExercise;
    }


    @GetMapping
    public PageResult<ExerciseDto> list(@RequestParam(required = false) String search,
                                        @RequestParam(required = false) String primaryMuscle,
                                        @RequestParam(required = false) String equipment,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "20") int size) {

        var result = listExercises.list(new ListExercisesQuery(search, primaryMuscle, equipment, page, size));
        var items = result.items().stream().map(ExerciseDto::from).toList();
        return new PageResult<>(items, result.total(), result.page(), result.size(),
                result.totalPages(), result.first(), result.last(),
                result.hasNext(), result.hasPrevious());
    }
    
    @PostMapping
    public ResponseEntity<ExerciseResponse> create(@Valid @RequestBody CreateExerciseRequest request) {
        var command = new CreateExerciseUseCase.CreateExerciseCommand(
            request.name(),
            request.primaryMuscle(),
            request.equipment(),
            request.unilateral()
        );
        
        var result = createExercise.create(command);
        var exercise = getExercise.get(new GetExerciseUseCase.GetExerciseCommand(result.exerciseId()));
        
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ExerciseResponse.from(exercise.exercise()));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ExerciseResponse> getById(@PathVariable UUID id) {
        var result = getExercise.get(new GetExerciseUseCase.GetExerciseCommand(id));
        return ResponseEntity.ok(ExerciseResponse.from(result.exercise()));
    }
    
    @GetMapping("/filters")
    public ResponseEntity<FilterOptionsResponse> getFilterOptions() {
        return ResponseEntity.ok(new FilterOptionsResponse(
            Stream.of(MuscleGroup.values()).map(Enum::name).collect(Collectors.toList()),
            Stream.of(Equipment.values()).map(Enum::name).collect(Collectors.toList())
        ));
    }
}
