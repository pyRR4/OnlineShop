package com.lukaszfabia.main.controller;

import com.lukaszfabia.main.dto.UserDTO;
import com.lukaszfabia.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller()
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(
            @RequestParam(value = "logout", required = false) String logout,
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "success", required = false) String success,
            Model model
    ) {
        model.addAttribute("userDTO", new UserDTO());

        model.addAttribute("body", "login");

        if (logout != null) {
            model.addAttribute("successMessage", "Wylogowano pomyślnie");
        }

        if (error != null) {
            model.addAttribute("errorMessage", "Niepoprawna nazwa użytkownika lub hasło");
        }

        if (success != null) {
            model.addAttribute("successMessage", "Account created successfully. You can now log in.");
        }

        return "layout/start_layout";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        model.addAttribute("body", "register");

        return "layout/start_layout";
    }

    @PostMapping("/register")
    public String registerUser(UserDTO userDTO, Model model) {
        try {
            userService.createUser(userDTO);
            return "redirect:/login?success=true";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred during registration.");
            model.addAttribute("body", "register");
            return "layout/start_layout";
        }
    }
}
