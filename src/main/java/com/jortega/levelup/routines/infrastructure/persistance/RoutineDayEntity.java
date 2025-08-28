package com.jortega.levelup.routines.infrastructure.persistance;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity @Table(name = "routine_day")
@Getter @Setter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RoutineDayEntity {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "routine_id", nullable = false)
    private RoutineEntity routine;

    @Column(name = "day_index", nullable = false) private int dayIndex;
    @Column(length = 80) private String name;

    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoutineExerciseEntity> exercises = new ArrayList<>();
}