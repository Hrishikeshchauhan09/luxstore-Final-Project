package com.example.luxestyle.controller;

import com.example.luxestyle.model.Order;
import com.example.luxestyle.model.User;
import com.example.luxestyle.model.enums.OrderStatus;
import com.example.luxestyle.repository.OrderRepository;
import com.example.luxestyle.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final com.example.luxestyle.repository.CartRepository cartRepository;
    private final com.example.luxestyle.repository.AddressRepository addressRepository;

    public OrderController(OrderRepository orderRepository, UserRepository userRepository,
            com.example.luxestyle.repository.CartRepository cartRepository,
            com.example.luxestyle.repository.AddressRepository addressRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.addressRepository = addressRepository;
    }

    @GetMapping
    public String viewOrders(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            User user = userRepository.findByUsername(username).orElse(null);
            if (user != null) {
                List<Order> orders = orderRepository.findByUserOrderByOrderDateDesc(user);
                model.addAttribute("orders", orders);
            }
        }
        return "orders";
    }

    @GetMapping("/checkout")
    public String checkout(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            User user = userRepository.findByUsername(username).orElse(null);

            if (user != null) {
                List<com.example.luxestyle.model.Cart> cartItems = cartRepository.findByUser(user);

                if (cartItems.isEmpty()) {
                    return "redirect:/cart";
                }

                double total = cartItems.stream()
                        .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                        .sum();

                model.addAttribute("total", total);
                model.addAttribute("user", user);
                model.addAttribute("addresses", addressRepository.findByUser(user));
                return "checkout";
            }
        }
        return "redirect:/login";
    }

    @PostMapping("/create")
    @Transactional
    public String createOrder(Principal principal,
            @RequestParam(required = false) Long selectedAddressId,
            @RequestParam(required = false) String shippingAddress,
            @RequestParam String paymentMethod) {

        if (principal != null) {
            String username = principal.getName();
            User user = userRepository.findByUsername(username).orElse(null);

            if (user != null) {
                List<com.example.luxestyle.model.Cart> cartItems = cartRepository.findByUser(user);

                if (cartItems.isEmpty()) {
                    return "redirect:/cart";
                }

                String finalShippingAddress = shippingAddress;
                // If manual address is empty, try to get from selected ID
                if ((finalShippingAddress == null || finalShippingAddress.trim().isEmpty())
                        && selectedAddressId != null) {
                    com.example.luxestyle.model.Address address = addressRepository.findById(selectedAddressId)
                            .orElse(null);
                    if (address != null) {
                        finalShippingAddress = address.getName() + ": " + address.getArea() + ", " + address.getCity()
                                + ", " + address.getState() + " - " + address.getPincode();
                    }
                }

                if (finalShippingAddress == null || finalShippingAddress.trim().isEmpty()) {
                    // Should return error
                    return "redirect:/orders/checkout?error=AddressRequired";
                }

                double totalAmount = cartItems.stream()
                        .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                        .sum();

                Order order = new Order();
                order.setUser(user);
                order.setTotalAmount(totalAmount);
                order.setShippingAddress(finalShippingAddress);
                order.setPaymentMethod(paymentMethod);
                order.setOrderDate(LocalDateTime.now());
                order.setStatus(OrderStatus.PENDING);

                orderRepository.save(order);

                // Clear cart
                cartRepository.deleteByUser(user);

                return "redirect:/orders/success";
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/success")
    public String orderSuccess(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            User user = userRepository.findByUsername(username).orElse(null);
            if (user != null) {
                List<Order> orders = orderRepository.findByUserOrderByOrderDateDesc(user);
                model.addAttribute("orders", orders);
            }
        }
        return "success";
    }

    @GetMapping("/admin/orders")
    public String viewAllOrders(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "admin/order/view_order";
    }
}
