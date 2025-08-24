package com.jortega.levelup.routines.infrastructure.persistance;

import com.jortega.levelup.routines.domain.Routine;
import com.jortega.levelup.routines.port.out.RoutineRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Transactional
public class RoutineRepositoryAdapter implements RoutineRepository {
    private final JpaRoutineRepository jpa;
    public RoutineRepositoryAdapter(JpaRoutineRepository jpa) { this.jpa = jpa; }

    @Override
    @Transactional
    public Routine save(Routine routine) {
        boolean exists = jpa.existsById(routine.getId());

        RoutineEntity entity;
        if (exists) {
            entity = jpa.findById(routine.getId())
                    .orElseThrow(() -> new IllegalStateException("Routine not found: " + routine.getId()));
            RoutineJpaMapper.toEntityUpdate(routine, entity);
        } else {
            entity = RoutineJpaMapper.toEntityNew(routine);
            entity.markNew();
        }

        RoutineEntity saved = jpa.save(entity);
        return RoutineJpaMapper.toDomain(saved);
    }

    @Override public Optional<Routine> findById(UUID id) {
        return jpa.findById(id).map(RoutineJpaMapper::toDomain);
    }

    @Override public List<Routine> findByOwner(UUID ownerUserId) {
        return jpa.findByOwnerUserId(ownerUserId).stream().map(RoutineJpaMapper::toDomain).toList();
    }
}