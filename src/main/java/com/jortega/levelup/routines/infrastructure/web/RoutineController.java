package com.jortega.levelup.routines.infrastructure.web;

import com.jortega.levelup.routines.port.in.CreateRoutineUseCase;
import com.jortega.levelup.routines.port.in.GetRoutineUseCase;
import com.jortega.levelup.routines.port.in.ListRoutinesUseCase;
import com.jortega.levelup.auth.infrastructure.SecurityContextCurrentUserProvider;
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
    private final SecurityContextCurrentUserProvider securityContextCurrentUserProvider;


    public RoutineController(CreateRoutineUseCase createRoutine,
                             GetRoutineUseCase getRoutine,
                             ListRoutinesUseCase listRoutines,
                             SecurityContextCurrentUserProvider securityContextCurrentUserProvider) {
        this.createRoutine = createRoutine;
        this.getRoutine = getRoutine;
        this.listRoutines = listRoutines;
        this.securityContextCurrentUserProvider = securityContextCurrentUserProvider;
    }


    @PostMapping
    public ResponseEntity<CreateRoutineResponse> create(@Valid @RequestBody CreateRoutineRequest req) {
        UUID userId = securityContextCurrentUserProvider.idOrThrow();
        UUID id = createRoutine.create(req.toCommand(userId));
        return ResponseEntity.status(201).body(new CreateRoutineResponse(id));
    }


    @GetMapping("/{id}")
    public ResponseEntity<RoutineResponse> get(@PathVariable UUID id) {
        UUID userId = securityContextCurrentUserProvider.idOrThrow();
        return getRoutine.get(id, userId)
                .map(r -> ResponseEntity.ok(RoutineWebMapper.toResponse(r)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping
    public List<RoutineSummary> listMine() {
        UUID userId = securityContextCurrentUserProvider.idOrThrow();
        return listRoutines.listMine(userId).stream().map(RoutineWebMapper::toSummary).toList();
    }
}
