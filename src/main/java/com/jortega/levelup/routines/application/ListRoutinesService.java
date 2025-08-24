package com.jortega.levelup.routines.application;

import com.jortega.levelup.routines.domain.Routine;
import com.jortega.levelup.routines.port.in.ListRoutinesUseCase;
import com.jortega.levelup.routines.port.out.RoutineRepository;

import java.util.List;
import java.util.UUID;

public class ListRoutinesService implements ListRoutinesUseCase {
    private final RoutineRepository routines;
    public ListRoutinesService(RoutineRepository routines) { this.routines = routines; }

    @Override
    public List<Routine> listMine(UUID ownerUserId) { return routines.findByOwner(ownerUserId); }
}
