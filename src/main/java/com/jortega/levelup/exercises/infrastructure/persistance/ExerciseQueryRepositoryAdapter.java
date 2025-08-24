package com.jortega.levelup.exercises.infrastructure.persistance;

import com.jortega.levelup.exercises.domain.Exercise;
import com.jortega.levelup.exercises.port.out.ExerciseQueryRepository;
import com.jortega.levelup.shared.domain.Equipment;
import com.jortega.levelup.shared.domain.MuscleGroup;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Transactional
public class ExerciseQueryRepositoryAdapter implements ExerciseQueryRepository {
    private final JpaExerciseRepository jpa;
    public ExerciseQueryRepositoryAdapter(JpaExerciseRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public List<Exercise> search(String search, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var p = (search == null || search.isBlank())
                ? jpa.findAll(pageable)
                : jpa.findByNameContainingIgnoreCase(search, pageable);
        return p.map(this::toDomain).toList();
    }

    @Override
    public long count(String search) {
        if (search == null || search.isBlank()) return jpa.count();
        return jpa.countByNameContainingIgnoreCase(search);
    }

    private Exercise toDomain(ExerciseEntity e) {
        return new Exercise(e.getId(), e.getName(), MuscleGroup.valueOf(e.getPrimaryMuscle()), Equipment.valueOf(e.getEquipment()), e.isUnilateral());
    }



}
