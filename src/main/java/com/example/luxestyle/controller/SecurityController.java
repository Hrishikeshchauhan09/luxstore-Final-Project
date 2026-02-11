package com.example.luxestyle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.luxestyle.model.User;
import com.example.luxestyle.repository.UserRepository;

@Controller
public class SecurityController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SecurityController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // your login.html
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register"; // your register.html
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
            @RequestParam String password) {

        // Create a new user with default role USER
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER");

        // Save to MySQL
        userRepository.save(user);

        // Redirect to login page after registration
        return "redirect:/login";
    }

}
