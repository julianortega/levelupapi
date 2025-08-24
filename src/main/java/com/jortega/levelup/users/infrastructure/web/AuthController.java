package com.jortega.levelup.users.infrastructure.web;

import com.jortega.levelup.users.port.in.LoginUserUseCase;
import com.jortega.levelup.users.port.in.RegisterUserUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;
    
    public AuthController(RegisterUserUseCase registerUserUseCase, LoginUserUseCase loginUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.loginUserUseCase = loginUserUseCase;
    }
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        var command = new RegisterUserUseCase.RegisterUserCommand(
            request.email(),
            request.username(),
            request.password()
        );
        
        var result = registerUserUseCase.register(command);
        
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new AuthResponse(result.userId(), result.email(), result.username(), null));
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        var command = new LoginUserUseCase.LoginUserCommand(
            request.email(),
            request.password()
        );
        
        var result = loginUserUseCase.login(command);
        
        return ResponseEntity.ok(new AuthResponse(
            result.userId(), 
            result.email(), 
            result.username(), 
            result.token()
        ));
    }
    
    public record RegisterRequest(String email, String username, String password) {}
    public record LoginRequest(String email, String password) {}
    public record AuthResponse(UUID userId, String email, String username, String token) {}
}
