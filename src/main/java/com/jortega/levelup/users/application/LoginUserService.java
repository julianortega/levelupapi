package com.jortega.levelup.users.application;

import com.jortega.levelup.shared.domain.exception.ValidationException;
import com.jortega.levelup.auth.infrastructure.JwtService;
import com.jortega.levelup.users.domain.User;
import com.jortega.levelup.users.domain.UserService;
import com.jortega.levelup.users.port.in.LoginUserUseCase;
import com.jortega.levelup.users.port.out.UserRepository;

import java.util.Objects;

public class LoginUserService implements LoginUserUseCase {
    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtService jwtService;
    
    public LoginUserService(UserRepository userRepository, UserService userService, JwtService jwtService) {
        this.userRepository = Objects.requireNonNull(userRepository);
        this.userService = Objects.requireNonNull(userService);
        this.jwtService = Objects.requireNonNull(jwtService);
    }
    
    @Override
    public Result login(LoginUserCommand command) {
        // Find user by email
        User user = userRepository.findByEmail(command.email())
            .orElseThrow(() -> new ValidationException("email", "Invalid email or password"));
        
        // Verify password
        if (!userService.verifyPassword(user, command.password())) {
            throw new ValidationException("password", "Invalid email or password");
        }
        
        // Generate JWT token
        String token = jwtService.generateToken(user.getId(), user.getEmail(), user.getUsername());
        
        return new Result(user.getId(), user.getEmail(), user.getUsername(), token);
    }
}
