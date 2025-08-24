package com.jortega.levelup.exercises.infrastructure.persistance;

import com.jortega.levelup.exercises.domain.model.Exercise;
import com.jortega.levelup.exercises.domain.ListExercisesQuery;
import com.jortega.levelup.exercises.api.dto.PageResult;
import com.jortega.levelup.exercises.port.out.ExerciseQueryRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;


@Component
@Transactional(readOnly = true)
public class ExerciseQueryRepositoryAdapter implements ExerciseQueryRepository {

    private final JpaExerciseRepository jpa;

    public ExerciseQueryRepositoryAdapter(JpaExerciseRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public PageResult<Exercise> search(ListExercisesQuery q) {
        int page = Math.max(0, q.page());
        int size = Math.min(Math.max(1, q.size()), 100);
        Pageable pageable = PageRequest.of(page, size);

        Specification<ExerciseEntity> spec = ExerciseSpecifications.build(
                q.search(),
                q.primaryMuscle(),
                q.equipment()
        );

        Page<ExerciseEntity> pageDb = jpa.findAll(spec, pageable);
        Page<Exercise> pageDomain = pageDb.map(this::toDomain);

        return new PageResult<>(
                pageDomain.getContent(),
                pageDb.getTotalElements(),
                pageDb.getNumber(),
                pageDb.getSize(),
                pageDb.getTotalPages(),
                pageDb.isFirst(),
                pageDb.isLast(),
                pageDb.hasNext(),
                pageDb.hasPrevious()
        );
    }

    private Exercise toDomain(ExerciseEntity e) {
        return new Exercise(e.getId(), e.getName(), e.getPrimaryMuscle(), e.getEquipment(), e.isUnilateral());
    }
}
