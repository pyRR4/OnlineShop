package com.lukaszfabia.main.exceptions;

public class ProductNotFound extends Exception {
    public ProductNotFound() {
    }

    public ProductNotFound(String message) {
        super(message);
    }
}
