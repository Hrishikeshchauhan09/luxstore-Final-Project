package com.example.luxestyle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoreController {

    private final com.example.luxestyle.repository.ProductRepository productRepository;

    public CoreController(com.example.luxestyle.repository.ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String home(org.springframework.ui.Model model) {
        // Fetch top 4 products for the home page
        java.util.List<com.example.luxestyle.model.Product> products = productRepository.findAll().stream().limit(4)
                .collect(java.util.stream.Collectors.toList());
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
}
