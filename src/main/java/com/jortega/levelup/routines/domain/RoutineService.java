package com.jortega.levelup.routines.domain;

import com.jortega.levelup.shared.domain.exception.ValidationException;

import java.util.UUID;

public class RoutineService {
    
    public Routine createRoutine(UUID ownerUserId, String name) {
        validateRoutineName(name);
        validateOwnerUserId(ownerUserId);
        return new Routine(null, ownerUserId, name);
    }
    
    public Routine createRoutineWithId(UUID id, UUID ownerUserId, String name) {
        validateRoutineName(name);
        validateOwnerUserId(ownerUserId);
        return new Routine(id, ownerUserId, name);
    }
    
    public void validateRoutineName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("name", "Routine name cannot be null or empty");
        }
        if (name.length() > 100) {
            throw new ValidationException("name", "Routine name cannot exceed 100 characters");
        }
    }
    
    public void validateOwnerUserId(UUID ownerUserId) {
        if (ownerUserId == null) {
            throw new ValidationException("ownerUserId", "Owner user ID cannot be null");
        }
    }
    
    public boolean canUserModifyRoutine(Routine routine, UUID userId) {
        return routine.getOwnerUserId().equals(userId);
    }
    
    public boolean isValidDayIndex(int dayIndex) {
        return dayIndex >= 1 && dayIndex <= 7;
    }
}
