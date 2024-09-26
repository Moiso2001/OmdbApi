package com.moises.becritic.exception;

/**
 * Exception thrown when a requested resource is not found.
 * This class extends the {@link RuntimeException} class.
 * 
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

