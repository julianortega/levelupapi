package com.jortega.levelup.exercises.port.in;

import com.jortega.levelup.exercises.domain.Exercise;

import java.util.List;
import java.util.Objects;

public interface ListExercisesUseCase {
    Result list(Query query);

    record Query(String search, int page, int size) {
        public Query(String search, int page, int size) {
            this.search = (search == null) ? "" : search;
            this.page = Math.max(0, page);
            this.size = (size < 1 || size > 100) ? 20 : size;
        }
    }

    record Result(List<Exercise> items, long total, int page, int size) {
        public Result(List<Exercise> items, long total, int page, int size) {
            this.items = Objects.requireNonNull(items);
            this.total = Math.max(0, total);
            this.page = Math.max(0, page);
            this.size = Math.max(1, size);
        }
    }
}