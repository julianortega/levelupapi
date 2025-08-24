package com.jortega.levelup.routines.port.in;

import com.jortega.levelup.routines.domain.Routine;

import java.util.List;
import java.util.UUID;

public interface ListRoutinesUseCase {
    List<Routine> listMine(UUID ownerUserId);
}