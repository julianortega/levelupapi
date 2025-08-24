package com.jortega.levelup.routines.infrastructure.persistance;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.*;
import java.util.UUID;

@Entity @Table(name = "routine_day", uniqueConstraints = @UniqueConstraint(name = "uk_routine_day_index", columnNames = {"routine_id","day_index"}))
@Getter @Setter
public class RoutineDayEntity {
    @Id @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name="routine_id", nullable=false)
    private RoutineEntity routine;

    @Column(name="day_index", nullable=false) private int dayIndex;
    @Column(length=80) private String name;

    @OneToMany(mappedBy="day", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<RoutineExerciseEntity> exercises = new ArrayList<>();

}