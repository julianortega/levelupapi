package com.jortega.levelup.exercises.application;

import com.jortega.levelup.exercises.domain.Exercise;
import com.jortega.levelup.exercises.port.in.ListExercisesUseCase;
import com.jortega.levelup.exercises.port.out.ExerciseQueryRepository;

import java.util.List;
import java.util.Objects;

public class ListExercisesService implements ListExercisesUseCase {
    private final ExerciseQueryRepository queries;
    public ListExercisesService(ExerciseQueryRepository queries) {
        this.queries = Objects.requireNonNull(queries);
    }

    @Override
    public Result list(Query q) {
        if (q == null) throw new IllegalArgumentException("query is null");
        String search = q.search();
        List<Exercise> items = queries.search(search, q.page(), q.size());
        long total = queries.count(search);
        return new Result(items, total, q.page(), q.size());
    }
}
