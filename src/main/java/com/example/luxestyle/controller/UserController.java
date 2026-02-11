package com.example.luxestyle.controller;

import com.example.luxestyle.model.User;
import com.example.luxestyle.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        // This seems to be a duplicate of SecurityController's registration
        // For now, let's just redirect to the main register endpoint or keep it if it's
        // used by a different flow
        // But better to use the SecurityController one.
        return "redirect:/register";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            User user = userRepository.findByUsername(username).orElse(null);
            model.addAttribute("user", user);
            return "profile";
        }
        return "redirect:/login";
    }
}
