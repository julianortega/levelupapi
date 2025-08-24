package com.jortega.levelup.routines.domain;

import java.util.*;
import java.util.UUID;

public final class Routine {
    private final UUID id;
    private final UUID ownerUserId;
    private String name;
    private final List<RoutineDay> days = new ArrayList<>();


    public Routine(UUID id, UUID ownerUserId, String name) {
        if (ownerUserId == null) throw new IllegalArgumentException("ownerUserId required");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("routine name required");
        this.id = id == null ? UUID.randomUUID() : id;
        this.ownerUserId = ownerUserId;
        this.name = name;
    }


    public UUID getId() { return id; }
    public UUID getOwnerUserId() { return ownerUserId; }
    public String getName() { return name; }
    public List<RoutineDay> getDays() { return Collections.unmodifiableList(days); }


    public void rename(String newName) {
        if (newName == null || newName.isBlank()) throw new IllegalArgumentException("name blank");
        this.name = newName;
    }


    public RoutineDay addDay(int dayIndex, String dayName) {
        if (dayIndex < 1 || dayIndex > 7) throw new IllegalArgumentException("dayIndex must be 1..7");
        if (days.stream().anyMatch(d -> d.getDayIndex() == dayIndex))
            throw new IllegalStateException("dayIndex already exists in routine");
        RoutineDay d = new RoutineDay(UUID.randomUUID(), dayIndex, dayName);
        this.days.add(d);
        this.days.sort(Comparator.comparingInt(RoutineDay::getDayIndex));
        return d;
    }
}
