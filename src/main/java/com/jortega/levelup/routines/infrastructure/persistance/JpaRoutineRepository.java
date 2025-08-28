package com.jortega.levelup.routines.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaRoutineRepository extends JpaRepository<RoutineEntity, UUID> {
    List<RoutineEntity> findByOwnerUserId(UUID ownerUserId);
    Optional<RoutineEntity> findByIdAndOwnerUserId(UUID id, UUID ownerUserId);
}
