package com.jortega.levelup.exercises.infrastructure.web;

import com.jortega.levelup.exercises.port.in.ListExercisesUseCase;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Validated
@RequestMapping("/api/exercises")
public class ExerciseController {
    private final ListExercisesUseCase listExercises;
    public ExerciseController(ListExercisesUseCase listExercises) { this.listExercises = listExercises; }


    @GetMapping
    public PageResponse<ExerciseResponse> list(
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "page", defaultValue = "0") @Min(0) int page,
            @RequestParam(name = "size", defaultValue = "20") @Min(1) int size) {
        var result = listExercises.list(new ListExercisesUseCase.Query(search, page, size));
        List<ExerciseResponse> items = result.items().stream().map(ExerciseResponse::from).collect(Collectors.toList());
        return new PageResponse<>(items, result.total(), result.page(), result.size());
    }
}
