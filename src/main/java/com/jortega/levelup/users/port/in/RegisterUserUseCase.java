package com.jortega.levelup.users.port.in;

import java.util.UUID;

public interface RegisterUserUseCase {
    Result register(RegisterUserCommand command);
    
    record RegisterUserCommand(String email, String username, String password) {
        public RegisterUserCommand {
            if (email == null || email.trim().isEmpty()) {
                throw new IllegalArgumentException("Email is required");
            }
            if (username == null || username.trim().isEmpty()) {
                throw new IllegalArgumentException("Username is required");
            }
            if (password == null || password.trim().isEmpty()) {
                throw new IllegalArgumentException("Password is required");
            }
        }
    }
    
    record Result(UUID userId, String email, String username) {
        public Result {
            if (userId == null) {
                throw new IllegalArgumentException("User ID cannot be null");
            }
            if (email == null || email.trim().isEmpty()) {
                throw new IllegalArgumentException("Email cannot be null or empty");
            }
            if (username == null || username.trim().isEmpty()) {
                throw new IllegalArgumentException("Username cannot be null or empty");
            }
        }
    }
}
