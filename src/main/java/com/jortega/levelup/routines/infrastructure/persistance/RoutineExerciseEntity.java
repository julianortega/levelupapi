package com.jortega.levelup.routines.infrastructure.persistance;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity @Table(name = "routine_exercises")
@Getter @Setter
public class RoutineExerciseEntity {
    @Id @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name="routine_day_id", nullable=false)
    private RoutineDayEntity day;

    @Column(name="exercise_id", nullable=false)
    private UUID exerciseId;

    @Column(name = "sets", nullable = false)
    private int targetSets;
    @Column(name = "reps_min", nullable = false)
    private int targetRepsMin;
    @Column(name = "reps_max", nullable = false)
    private int targetRepsMax;
    
}
