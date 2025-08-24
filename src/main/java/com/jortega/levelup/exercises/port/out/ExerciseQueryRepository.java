package com.jortega.levelup.exercises.port.out;

import com.jortega.levelup.exercises.domain.Exercise;
import java.util.List;

public interface ExerciseQueryRepository {
    List<Exercise> search(String search, int page, int size);
    long count(String search);
}