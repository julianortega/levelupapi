package com.jortega.levelup.routines.infrastructure.persistance;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity @Table(name = "routine_exercise")
@Getter @Setter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RoutineExerciseEntity {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "routine_day_id", nullable = false)
    private RoutineDayEntity day;

    @Column(name = "exercise_id", nullable = false) private UUID exerciseId;
    @Column(nullable = false) private int sets;
    @Column(nullable = false) private int repsMin;
    @Column(nullable = false) private int repsMax;
    @Column(name = "rest_seconds", nullable = false) private int restSeconds;
    @Column private String notes;
}
