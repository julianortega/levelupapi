package com.jortega.levelup.users.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public final class User {
    private final UUID id;
    private final String email;
    private final String username;
    private final String passwordHash;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public User(UUID id, String email, String username, String passwordHash, 
                LocalDateTime createdAt, LocalDateTime updatedAt) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (passwordHash == null || passwordHash.trim().isEmpty()) {
            throw new IllegalArgumentException("Password hash cannot be null or empty");
        }
        
        this.id = id == null ? UUID.randomUUID() : id;
        this.email = email.toLowerCase().trim();
        this.username = username.trim();
        this.passwordHash = passwordHash;
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
        this.updatedAt = updatedAt == null ? LocalDateTime.now() : updatedAt;
    }

    public UUID getId() { return id; }
    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public String getPasswordHash() { return passwordHash; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
