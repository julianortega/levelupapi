package com.jortega.levelup.routines.infrastructure.web;

import java.util.List;
import java.util.UUID;

public record RoutineResponse(UUID id, String name, UUID ownerUserId, List<Day> days) {
    public static record Day(UUID id, int dayIndex, String name, List<Ex> exercises) {}
    public static record Ex(UUID id, UUID exerciseId, int targetSets, int targetRepsMin, int targetRepsMax, String notes) {}
}