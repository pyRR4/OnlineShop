package com.lukaszfabia.main.exceptions;

public class RoleNotFound extends RuntimeException {
    public RoleNotFound(String rolename) {
        super(String.format("Role not found: %s", rolename));
    }
}
