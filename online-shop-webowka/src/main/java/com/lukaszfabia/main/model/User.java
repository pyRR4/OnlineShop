package com.lukaszfabia.main.model;

import com.lukaszfabia.main.dto.UserDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role", nullable = false, referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Role role;

    public User(UserDTO userDTO) {
        this.username = userDTO.username();
        this.password = userDTO.password();
        this.role = userDTO.role();
    }

    public User() {
        this(new UserDTO());
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
