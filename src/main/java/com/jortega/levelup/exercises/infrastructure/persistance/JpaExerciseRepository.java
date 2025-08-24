package com.jortega.levelup.exercises.infrastructure.persistance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaExerciseRepository extends JpaRepository<ExerciseEntity, UUID> {
    Page<ExerciseEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
    long countByNameContainingIgnoreCase(String name);
}
