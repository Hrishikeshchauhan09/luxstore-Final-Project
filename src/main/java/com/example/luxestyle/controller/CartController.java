package com.example.luxestyle.controller;

import com.example.luxestyle.model.Cart;
import com.example.luxestyle.model.Product;
import com.example.luxestyle.model.User;
import com.example.luxestyle.repository.CartRepository;
import com.example.luxestyle.repository.ProductRepository;
import com.example.luxestyle.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartController(CartRepository cartRepository, ProductRepository productRepository,
            UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String viewCart(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            User user = userRepository.findByUsername(username).orElse(null);

            if (user != null) {
                List<Cart> cartItems = cartRepository.findByUser(user);
                model.addAttribute("cartItems", cartItems);

                double total = cartItems.stream()
                        .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                        .sum();
                model.addAttribute("total", total);
            }
        }
        return "cart";
    }

    @GetMapping("/add/{productId}")
    public String addToCart(@PathVariable Long productId, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            User user = userRepository.findByUsername(username).orElse(null);
            Product product = productRepository.findById(productId).orElse(null);

            if (user != null && product != null) {
                // Check if product already in cart
                Cart existingCart = cartRepository.findByUserAndProduct(user, product);
                if (existingCart != null) {
                    existingCart.setQuantity(existingCart.getQuantity() + 1);
                    cartRepository.save(existingCart);
                } else {
                    Cart cart = new Cart();
                    cart.setUser(user);
                    cart.setProduct(product);
                    cart.setQuantity(1);
                    cartRepository.save(cart);
                }
            }
        }
        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Long id) {
        cartRepository.deleteById(id);
        return "redirect:/cart";
    }

    @GetMapping("/increase/{id}")
    public String increaseQuantity(@PathVariable Long id) {
        Cart cart = cartRepository.findById(id).orElse(null);
        if (cart != null) {
            cart.setQuantity(cart.getQuantity() + 1);
            cartRepository.save(cart);
        }
        return "redirect:/cart";
    }

    @GetMapping("/decrease/{id}")
    public String decreaseQuantity(@PathVariable Long id) {
        Cart cart = cartRepository.findById(id).orElse(null);
        if (cart != null) {
            if (cart.getQuantity() > 1) {
                cart.setQuantity(cart.getQuantity() - 1);
                cartRepository.save(cart);
            } else {
                // If quantity is 1, remove the item
                cartRepository.deleteById(id);
            }
        }
        return "redirect:/cart";
    }
}
