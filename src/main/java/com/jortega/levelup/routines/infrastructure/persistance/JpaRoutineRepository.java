package com.jortega.levelup.routines.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaRoutineRepository extends JpaRepository<RoutineEntity, UUID> {
    List<RoutineEntity> findByOwnerUserId(UUID ownerUserId);
}
