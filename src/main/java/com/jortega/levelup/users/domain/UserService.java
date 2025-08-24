package com.jortega.levelup.users.domain;

import com.jortega.levelup.shared.domain.exception.ValidationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class UserService {
    private final PasswordEncoder passwordEncoder;
    
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
    public User createUser(String email, String username, String password) {
        validateEmail(email);
        validateUsername(username);
        validatePassword(password);
        
        String passwordHash = passwordEncoder.encode(password);
        return new User(null, email, username, passwordHash, null, null);
    }
    
    public User createUserWithId(UUID id, String email, String username, String passwordHash) {
        validateEmail(email);
        validateUsername(username);
        
        return new User(id, email, username, passwordHash, null, null);
    }
    
    public boolean verifyPassword(User user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPasswordHash());
    }
    
    private void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new ValidationException("email", "Email cannot be null or empty");
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new ValidationException("email", "Invalid email format");
        }
        if (email.length() > 255) {
            throw new ValidationException("email", "Email cannot exceed 255 characters");
        }
    }
    
    private void validateUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new ValidationException("username", "Username cannot be null or empty");
        }
        if (username.length() < 3) {
            throw new ValidationException("username", "Username must be at least 3 characters");
        }
        if (username.length() > 50) {
            throw new ValidationException("username", "Username cannot exceed 50 characters");
        }
        if (!username.matches("^[a-zA-Z0-9_]+$")) {
            throw new ValidationException("username", "Username can only contain letters, numbers, and underscores");
        }
    }
    
    private void validatePassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new ValidationException("password", "Password cannot be null or empty");
        }
        if (password.length() < 8) {
            throw new ValidationException("password", "Password must be at least 8 characters");
        }
        if (password.length() > 128) {
            throw new ValidationException("password", "Password cannot exceed 128 characters");
        }
    }
}
