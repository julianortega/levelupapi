package com.jortega.levelup.routines.port.out;

import com.jortega.levelup.routines.domain.Routine;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoutineRepository {
    Routine save(Routine routine);
    Optional<Routine> findById(UUID id);
    List<Routine> findByOwner(UUID ownerUserId);
}