package com.jortega.levelup.shared.domain.exception;

public class ValidationException extends DomainException {
    
    public ValidationException(String message) {
        super(message);
    }
    
    public ValidationException(String field, String message) {
        super(String.format("Validation error for field '%s': %s", field, message));
    }
}
