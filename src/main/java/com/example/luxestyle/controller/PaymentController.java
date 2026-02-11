package com.example.luxestyle.controller;

import com.example.luxestyle.model.Address;
import com.example.luxestyle.model.Cart;
import com.example.luxestyle.model.User;
import com.example.luxestyle.repository.AddressRepository;
import com.example.luxestyle.repository.CartRepository;
import com.example.luxestyle.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class PaymentController {

    private final AddressRepository addressRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public PaymentController(AddressRepository addressRepository,
            CartRepository cartRepository,
            UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/payment")
    public String paymentPage(
            @RequestParam("selectedAddressId") Long addressId,
            Model model,
            Principal principal) {

        if (principal == null) {
            return "redirect:/login";
        }

        String username = principal.getName();
        User user = userRepository.findByUsername(username).orElse(null);

        if (user == null) {
            return "redirect:/login";
        }

        // Get selected address
        Address address = addressRepository.findById(addressId).orElse(null);
        if (address == null) {
            return "redirect:/orders/checkout?error=InvalidAddress";
        }

        // Get cart items
        List<Cart> cartItems = cartRepository.findByUser(user);
        if (cartItems.isEmpty()) {
            return "redirect:/cart";
        }

        // Calculate total
        double total = cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        // Add to model
        model.addAttribute("selectedAddress", address);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);
        model.addAttribute("shipping", 50.0); // Fixed shipping cost
        model.addAttribute("finalTotal", total + 50.0);
        model.addAttribute("user", user);

        return "payment";
    }
}
