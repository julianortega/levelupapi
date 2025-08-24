package com.jortega.levelup.routines.infrastructure.persistance;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity @Table(name = "routine_exercise")
@Getter @Setter
public class RoutineExerciseEntity {
    @Id @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name="day_id", nullable=false)
    private RoutineDayEntity day;

    @Column(name="exercise_id", nullable=false)
    private UUID exerciseId;


    @Column(name = "target_sets", nullable = false)
    private int targetSets;
    @Column(name = "target_reps_min", nullable = false)
    private int targetRepsMin;
    @Column(name = "target_reps_max", nullable = false)
    private int targetRepsMax;
    @Column(columnDefinition = "text")
    private String notes;

}
