package com.jortega.levelup.users.application;

import com.jortega.levelup.shared.domain.exception.ValidationException;
import com.jortega.levelup.users.domain.User;
import com.jortega.levelup.users.domain.UserService;
import com.jortega.levelup.users.port.in.RegisterUserUseCase;
import com.jortega.levelup.users.port.out.UserRepository;

import java.util.Objects;

public class RegisterUserService implements RegisterUserUseCase {
    private final UserRepository userRepository;
    private final UserService userService;
    
    public RegisterUserService(UserRepository userRepository, UserService userService) {
        this.userRepository = Objects.requireNonNull(userRepository);
        this.userService = Objects.requireNonNull(userService);
    }
    
    @Override
    public Result register(RegisterUserCommand command) {
        // Check if user already exists
        if (userRepository.existsByEmail(command.email())) {
            throw new ValidationException("email", "User with this email already exists");
        }
        
        if (userRepository.existsByUsername(command.username())) {
            throw new ValidationException("username", "Username is already taken");
        }
        
        // Create user
        User user = userService.createUser(command.email(), command.username(), command.password());
        User savedUser = userRepository.save(user);
        
        return new Result(savedUser.getId(), savedUser.getEmail(), savedUser.getUsername());
    }
}
