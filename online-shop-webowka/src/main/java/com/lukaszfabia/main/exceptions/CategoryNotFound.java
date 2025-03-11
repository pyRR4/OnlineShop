package com.lukaszfabia.main.exceptions;

public class CategoryNotFound extends Exception {
    public CategoryNotFound() {}

    public CategoryNotFound(String message) {
        super(message);
    }
}
