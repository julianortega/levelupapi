package com.jortega.levelup.exercises.application;

import com.jortega.levelup.exercises.domain.model.Exercise;
import com.jortega.levelup.exercises.domain.ListExercisesQuery;
import com.jortega.levelup.exercises.api.dto.PageResult;
import com.jortega.levelup.exercises.port.in.ListExercisesUseCase;
import com.jortega.levelup.exercises.port.out.ExerciseQueryRepository;

import java.util.Objects;

public class ListExercisesService implements ListExercisesUseCase {

    private final ExerciseQueryRepository queries;

    public ListExercisesService(ExerciseQueryRepository queries) {
        this.queries = Objects.requireNonNull(queries);
    }

    @Override
    public PageResult<Exercise> list(ListExercisesQuery q) {
        if (q == null) throw new IllegalArgumentException("query is null");
        // Reglas de negocio extra aquí si las hubiera (caps de size, límites, etc.)
        return queries.search(q);
    }
}