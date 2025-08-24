package com.jortega.levelup.routines.infrastructure.web;

import com.jortega.levelup.routines.port.in.CreateRoutineUseCase;
import com.jortega.levelup.routines.port.in.GetRoutineUseCase;
import com.jortega.levelup.routines.port.in.ListRoutinesUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/routines")
public class RoutineController {
    private final CreateRoutineUseCase createRoutine;
    private final GetRoutineUseCase getRoutine;
    private final ListRoutinesUseCase listRoutines;


    public RoutineController(CreateRoutineUseCase createRoutine, GetRoutineUseCase getRoutine, ListRoutinesUseCase listRoutines) {
        this.createRoutine = createRoutine; this.getRoutine = getRoutine; this.listRoutines = listRoutines;
    }


    @PostMapping
    public ResponseEntity<CreateRoutineResponse> create(@RequestHeader("X-User-Id") UUID userId,
                                                        @Valid @RequestBody CreateRoutineRequest req) {
        UUID id = createRoutine.create(req.toCommand(userId));
        return ResponseEntity.status(201).body(new CreateRoutineResponse(id));
    }


    @GetMapping("/{id}")
    public ResponseEntity<RoutineResponse> get(@RequestHeader("X-User-Id") UUID userId,
                                               @PathVariable UUID id) {
        return getRoutine.get(id, userId)
                .map(r -> ResponseEntity.ok(RoutineWebMapper.toResponse(r)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping
    public List<RoutineSummary> listMine(@RequestHeader("X-User-Id") UUID userId) {
        return listRoutines.listMine(userId).stream().map(RoutineWebMapper::toSummary).toList();
    }
}
