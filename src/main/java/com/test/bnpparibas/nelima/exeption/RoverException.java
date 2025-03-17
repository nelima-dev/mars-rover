package com.test.bnpparibas.nelima.exeption;

/**
 * Custom exception class for Rover-related errors.
 */
public class RoverException extends RuntimeException {
    public RoverException(String message) {
        super(message);
    }
}
