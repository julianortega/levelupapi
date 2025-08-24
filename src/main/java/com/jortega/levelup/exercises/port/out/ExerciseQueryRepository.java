package com.jortega.levelup.exercises.port.out;

import com.jortega.levelup.exercises.domain.model.Exercise;
import com.jortega.levelup.exercises.domain.ListExercisesQuery;
import com.jortega.levelup.exercises.api.dto.PageResult;

public interface ExerciseQueryRepository {
    PageResult<Exercise> search(ListExercisesQuery q);
}