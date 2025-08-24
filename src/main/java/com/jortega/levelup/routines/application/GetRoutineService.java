package com.jortega.levelup.routines.application;

import com.jortega.levelup.routines.domain.Routine;
import com.jortega.levelup.routines.port.in.GetRoutineUseCase;
import com.jortega.levelup.routines.port.out.RoutineRepository;

import java.util.Optional;
import java.util.UUID;

public class GetRoutineService implements GetRoutineUseCase {
    private final RoutineRepository routines;
    public GetRoutineService(RoutineRepository routines) { this.routines = routines; }


    @Override
    public Optional<Routine> get(UUID routineId, UUID requesterUserId) {
        return routines.findById(routineId).filter(r -> r.getOwnerUserId().equals(requesterUserId));
    }
}
