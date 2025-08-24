package com.jortega.levelup.exercises.infrastructure.persistance;

import jakarta.persistence.*;
import java.util.UUID;

@Entity @Table(name = "exercise")
public class ExerciseEntity {
    @Id private UUID id; // catálogo preseeded usa IDs estables
    @Column(nullable = false) private String name;
    @Column(name = "primary_muscle", nullable = false) private String primaryMuscle;
    @Column(nullable = false) private String equipment;
    @Column(nullable = false) private boolean unilateral;


    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPrimaryMuscle() { return primaryMuscle; }
    public void setPrimaryMuscle(String primaryMuscle) { this.primaryMuscle = primaryMuscle; }
    public String getEquipment() { return equipment; }
    public void setEquipment(String equipment) { this.equipment = equipment; }
    public boolean isUnilateral() { return unilateral; }
    public void setUnilateral(boolean unilateral) { this.unilateral = unilateral; }
}
