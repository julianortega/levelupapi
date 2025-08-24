package com.jortega.levelup.exercises.port.in;

import com.jortega.levelup.exercises.domain.model.Exercise;
import com.jortega.levelup.exercises.domain.ListExercisesQuery;
import com.jortega.levelup.exercises.api.dto.PageResult;

public interface ListExercisesUseCase {
    PageResult<Exercise> list(ListExercisesQuery query);
}