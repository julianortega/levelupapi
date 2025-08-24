package com.jortega.levelup.exercises.infrastructure.persistance;

import com.jortega.levelup.shared.domain.Equipment;
import com.jortega.levelup.shared.domain.MuscleGroup;
import org.springframework.data.jpa.domain.Specification;

public final class ExerciseSpecifications {
    private ExerciseSpecifications() {}

    public static Specification<ExerciseEntity> build(String search, String primaryMuscle, String equipment) {
        return (root, cq, cb) -> {
            var predicate = cb.conjunction();

            if (search != null && !search.isBlank()) {
                predicate = cb.and(predicate,
                        cb.like(cb.lower(root.get("name")), "%" + search.trim().toLowerCase() + "%"));
            }

            if (primaryMuscle != null && !primaryMuscle.isBlank()) {
                predicate = cb.and(predicate,
                        cb.equal(root.get("primaryMuscle"),
                                MuscleGroup.valueOf(primaryMuscle.trim().toUpperCase())));
            }

            if (equipment != null && !equipment.isBlank()) {
                predicate = cb.and(predicate,
                        cb.equal(root.get("equipment"),
                                Equipment.valueOf(equipment.trim().toUpperCase())));
            }

            return predicate;
        };
    }
}
