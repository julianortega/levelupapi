package com.jortega.levelup.routines.infrastructure.persistance;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity @Table(name = "routine")
@Getter @Setter
public class RoutineEntity implements Persistable<UUID> {

    @Id
    private UUID id;

    @Column(name = "owner_user_id", nullable = false)
    private UUID ownerUserId;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("dayIndex ASC")
    private List<RoutineDayEntity> days = new ArrayList<>();

    @Transient
    private boolean isNew;

    @Override public boolean isNew() { return isNew || id == null; }
    public void markNew() { this.isNew = true; }
}
