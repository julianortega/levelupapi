package com.jortega.levelup.users.port.in;

import java.util.UUID;

public interface LoginUserUseCase {
    Result login(LoginUserCommand command);
    
    record LoginUserCommand(String email, String password) {
        public LoginUserCommand {
            if (email == null || email.trim().isEmpty()) {
                throw new IllegalArgumentException("Email is required");
            }
            if (password == null || password.trim().isEmpty()) {
                throw new IllegalArgumentException("Password is required");
            }
        }
    }
    
    record Result(UUID userId, String email, String username, String token) {
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
            if (token == null || token.trim().isEmpty()) {
                throw new IllegalArgumentException("Token cannot be null or empty");
            }
        }
    }
}
