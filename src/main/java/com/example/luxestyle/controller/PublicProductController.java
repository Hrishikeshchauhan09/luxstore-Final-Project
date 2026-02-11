package com.example.luxestyle.controller;

import com.example.luxestyle.model.Product;
import com.example.luxestyle.model.enums.Category;
import com.example.luxestyle.model.enums.Gender;
import com.example.luxestyle.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class PublicProductController {

    private final ProductRepository productRepository;

    public PublicProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String viewProducts(Model model,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String keyword) {
        List<Product> products;

        if (keyword != null && !keyword.isEmpty()) {
            products = productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword,
                    keyword);
        } else if (category != null && gender != null) {
            products = productRepository.findByCategoryAndGender(
                    Category.valueOf(category.toUpperCase()),
                    Gender.valueOf(gender.toUpperCase()));
        } else if (category != null) {
            products = productRepository.findByCategory(Category.valueOf(category.toUpperCase()));
        } else if (gender != null) {
            products = productRepository.findByGender(Gender.valueOf(gender.toUpperCase()));
        } else {
            products = productRepository.findAll();
        }

        model.addAttribute("products", products);
        model.addAttribute("categories", Category.values());
        model.addAttribute("genders", Gender.values());
        model.addAttribute("keyword", keyword);
        return "products";
    }

    @GetMapping("/{id}")
    public String productDetails(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id).orElse(null);
        model.addAttribute("product", product);
        return "product-details";
    }

    @GetMapping("/image/{id}")
    @ResponseBody
    public byte[] getImage(@PathVariable Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        return product.getImage();
    }
}
