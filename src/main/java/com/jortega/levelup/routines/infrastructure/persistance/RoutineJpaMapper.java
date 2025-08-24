package com.jortega.levelup.routines.infrastructure.persistance;

import com.jortega.levelup.routines.domain.Routine;
import com.jortega.levelup.routines.domain.RoutineDay;
import com.jortega.levelup.routines.domain.RoutineExercise;

import java.util.*;
import java.util.stream.Collectors;

public final class RoutineJpaMapper {
    private RoutineJpaMapper() {}

    public static Routine toDomain(RoutineEntity e) {
        Routine r = new Routine(e.getId(), e.getOwnerUserId(), e.getName());
        for (RoutineDayEntity de : e.getDays()) {
            RoutineDay d = new RoutineDay(de.getId(), de.getDayIndex(), de.getName());
            for (RoutineExerciseEntity re : de.getExercises()) {
                RoutineExercise rx = new RoutineExercise(re.getId(), re.getExerciseId(), re.getTargetSets(), re.getTargetRepsMin(), re.getTargetRepsMax(), re.getNotes());
                d.addExercise(re.getExerciseId(), re.getTargetSets(), re.getTargetRepsMin(), re.getTargetRepsMax());
            }
            r.addDay(d.getDayIndex(), d.getName());
        }
        return r;
    }

    public static RoutineEntity toEntity(Routine r) {
        RoutineEntity e = new RoutineEntity();
        e.setId(r.getId()); e.setOwnerUserId(r.getOwnerUserId()); e.setName(r.getName());
        List<RoutineDayEntity> dayEntities = new ArrayList<>();
        for (RoutineDay d : r.getDays()) {
            RoutineDayEntity de = new RoutineDayEntity();
            de.setId(d.getId()); de.setRoutine(e); de.setDayIndex(d.getDayIndex()); de.setName(d.getName());
            List<RoutineExerciseEntity> exEntities = new ArrayList<>();
            for (RoutineExercise rx : d.getExercises()) {
                RoutineExerciseEntity re = new RoutineExerciseEntity();
                re.setId(rx.getId());
                mapRoutineExerciseToEntity(de, exEntities, rx, re);
            }
            de.setExercises(exEntities);
            dayEntities.add(de);
        }
        e.setDays(dayEntities);
        return e;
    }

    public static RoutineEntity toEntityNew(Routine r) {
        RoutineEntity e = new RoutineEntity();
        e.setId(r.getId());
        e.setOwnerUserId(r.getOwnerUserId());
        e.setName(r.getName());
        e.markNew();

        List<RoutineDayEntity> dayEntities = new ArrayList<>();
        for (RoutineDay d : r.getDays()) {
            RoutineDayEntity de = new RoutineDayEntity();
            de.setRoutine(e);
            de.setDayIndex(d.getDayIndex());
            de.setName(d.getName());

            List<RoutineExerciseEntity> exEntities = new ArrayList<>();
            for (RoutineExercise rx : d.getExercises()) {
                RoutineExerciseEntity re = new RoutineExerciseEntity();
                mapRoutineExerciseToEntity(de, exEntities, rx, re);
            }
            de.setExercises(exEntities);
            dayEntities.add(de);
        }
        e.setDays(dayEntities);
        return e;
    }

    private static void mapRoutineExerciseToEntity(RoutineDayEntity de, List<RoutineExerciseEntity> exEntities, RoutineExercise rx, RoutineExerciseEntity re) {
        re.setDay(de);
        re.setExerciseId(rx.getExerciseId());
        re.setTargetSets(rx.getTargetSets());
        re.setTargetRepsMin(rx.getTargetRepsMin());
        re.setTargetRepsMax(rx.getTargetRepsMax());
        re.setNotes(rx.getNotes());
        exEntities.add(re);
    }

    public static RoutineEntity toEntityUpdate(Routine source, RoutineEntity targetManaged) {
        targetManaged.setName(source.getName());
        targetManaged.setOwnerUserId(source.getOwnerUserId());

        Map<Integer, RoutineDayEntity> existingByIndex = targetManaged.getDays()
                .stream().collect(Collectors.toMap(RoutineDayEntity::getDayIndex, d -> d));

        Set<Integer> keepIndexes = new HashSet<>();

        for (RoutineDay d : source.getDays()) {
            keepIndexes.add(d.getDayIndex());

            RoutineDayEntity dayEntity = existingByIndex.get(d.getDayIndex());
            if (dayEntity == null) {
                dayEntity = new RoutineDayEntity();
                dayEntity.setRoutine(targetManaged);
                dayEntity.setDayIndex(d.getDayIndex());
                targetManaged.getDays().add(dayEntity);
            }
            dayEntity.setName(d.getName());

            dayEntity.getExercises().clear();
            for (RoutineExercise rx : d.getExercises()) {
                RoutineExerciseEntity re = new RoutineExerciseEntity();
                re.setDay(dayEntity);
                re.setExerciseId(rx.getExerciseId());
                re.setTargetSets(rx.getTargetSets());
                re.setTargetRepsMin(rx.getTargetRepsMin());
                re.setTargetRepsMax(rx.getTargetRepsMax());
                re.setNotes(rx.getNotes());
                dayEntity.getExercises().add(re);
            }
        }

        targetManaged.getDays().removeIf(de -> !keepIndexes.contains(de.getDayIndex()));

        return targetManaged;
    }
}
