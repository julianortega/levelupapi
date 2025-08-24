package com.jortega.levelup.exercises.domain;

import com.jortega.levelup.shared.domain.Equipment;
import com.jortega.levelup.shared.domain.MuscleGroup;

import java.util.UUID;

public final class Exercise {
    private final UUID id;
    private final String name;
    private final MuscleGroup primaryMuscle;
    private final Equipment equipment;
    private final boolean unilateral;


    public Exercise(UUID id, String name, MuscleGroup primaryMuscle, Equipment equipment, boolean unilateral) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name required");
        this.id = id == null ? UUID.randomUUID() : id;
        this.name = name;
        this.primaryMuscle = primaryMuscle == null ? MuscleGroup.OTHER : primaryMuscle;
        this.equipment = equipment == null ? Equipment.OTHER : equipment;
        this.unilateral = unilateral;
    }


    public UUID getId() { return id; }
    public String getName() { return name; }
    public MuscleGroup getPrimaryMuscle() { return primaryMuscle; }
    public Equipment getEquipment() { return equipment; }
    public boolean isUnilateral() { return unilateral; }

}