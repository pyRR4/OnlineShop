package com.lukaszfabia.main.dto;

import com.lukaszfabia.main.model.Role;
import com.lukaszfabia.main.model.User;

public record UserDTO(
        Integer id,
        String username,
        String password,
        Role role
) {
    public UserDTO(Integer id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserDTO(User user) {
        this(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole()
        );
    }

    public UserDTO() {
        this(0, null, null, null);
    }
}
