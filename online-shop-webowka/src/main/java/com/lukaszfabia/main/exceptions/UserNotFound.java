package com.lukaszfabia.main.exceptions;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String username) {
        super(String.format("User not found: %s", username));
    }
}
