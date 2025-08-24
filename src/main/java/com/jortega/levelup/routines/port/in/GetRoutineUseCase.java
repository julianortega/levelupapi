package com.jortega.levelup.routines.port.in;

import com.jortega.levelup.routines.domain.Routine;

import java.util.Optional;
import java.util.UUID;

public interface GetRoutineUseCase {
    Optional<Routine> get(UUID routineId, UUID requesterUserId);
}
