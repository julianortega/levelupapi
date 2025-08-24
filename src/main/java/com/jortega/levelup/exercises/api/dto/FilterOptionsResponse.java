package com.jortega.levelup.exercises.api.dto;

import java.util.List;

public class FilterOptionsResponse {
    private final List<String> muscleGroups;
    private final List<String> equipment;

    public FilterOptionsResponse(List<String> muscleGroups, List<String> equipment) {
        this.muscleGroups = muscleGroups;
        this.equipment = equipment;
    }

    public List<String> getMuscleGroups() { return muscleGroups; }
    public List<String> getEquipment() { return equipment; }
}