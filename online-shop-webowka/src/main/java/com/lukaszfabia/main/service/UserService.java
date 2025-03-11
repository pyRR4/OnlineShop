package com.lukaszfabia.main.service;

import com.lukaszfabia.main.dto.UserDTO;
import com.lukaszfabia.main.exceptions.RoleNotFound;
import com.lukaszfabia.main.exceptions.UserNotFound;
import com.lukaszfabia.main.model.Role;
import com.lukaszfabia.main.model.User;
import com.lukaszfabia.main.repository.RoleRepository;
import com.lukaszfabia.main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFound(username));

        return new UserDTO(user);
    }

    public void createUser(UserDTO userDTO) {
        User user = new User(userDTO);

        Role role = roleRepository.findByName("user")
                .orElseThrow(() -> new RoleNotFound("user"));
        user.setRole(role);

        userRepository.save(user);
    }
}
