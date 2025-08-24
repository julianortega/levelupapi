package com.jortega.levelup.exercises.infrastructure.persistance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jortega.levelup.shared.domain.Equipment;
import com.jortega.levelup.shared.domain.MuscleGroup;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface JpaExerciseRepository extends JpaRepository<ExerciseEntity, UUID>, JpaSpecificationExecutor<ExerciseEntity> {
    // Search by name only
    Page<ExerciseEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
    long countByNameContainingIgnoreCase(String name);
    
    // Search by name + muscle filter
    Page<ExerciseEntity> findByNameContainingIgnoreCaseAndPrimaryMuscle(String name, MuscleGroup primaryMuscle, Pageable pageable);
    long countByNameContainingIgnoreCaseAndPrimaryMuscle(String name, MuscleGroup primaryMuscle);
    
    // Search by name + equipment filter
    Page<ExerciseEntity> findByNameContainingIgnoreCaseAndEquipment(String name, Equipment equipment, Pageable pageable);
    long countByNameContainingIgnoreCaseAndEquipment(String name, Equipment equipment);
    
    // Search by name + muscle + equipment filters
    Page<ExerciseEntity> findByNameContainingIgnoreCaseAndPrimaryMuscleAndEquipment(String name, MuscleGroup primaryMuscle, Equipment equipment, Pageable pageable);
    long countByNameContainingIgnoreCaseAndPrimaryMuscleAndEquipment(String name, MuscleGroup primaryMuscle, Equipment equipment);
    
    // Filter by muscle only
    Page<ExerciseEntity> findByPrimaryMuscle(MuscleGroup primaryMuscle, Pageable pageable);
    long countByPrimaryMuscle(MuscleGroup primaryMuscle);
    
    // Filter by equipment only
    Page<ExerciseEntity> findByEquipment(Equipment equipment, Pageable pageable);
    long countByEquipment(Equipment equipment);
    
    // Filter by muscle + equipment
    Page<ExerciseEntity> findByPrimaryMuscleAndEquipment(MuscleGroup primaryMuscle, Equipment equipment, Pageable pageable);
    long countByPrimaryMuscleAndEquipment(MuscleGroup primaryMuscle, Equipment equipment);
}
