package com.jortega.levelup.exercises.infrastructure.persistance;

import com.jortega.levelup.shared.domain.Equipment;
import com.jortega.levelup.shared.domain.MuscleGroup;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity @Table(name = "exercises")
@Getter @Setter
public class ExerciseEntity {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(name = "primary_muscle", nullable = false)
    @Enumerated(EnumType.STRING)
    private MuscleGroup primaryMuscle;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Equipment equipment;

    @Column(nullable = false)
    private boolean unilateral;
}
