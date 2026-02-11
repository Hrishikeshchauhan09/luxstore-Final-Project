package com.example.luxestyle.controller;

import com.example.luxestyle.model.Address;
import com.example.luxestyle.model.User;
import com.example.luxestyle.model.enums.State;
import com.example.luxestyle.repository.AddressRepository;
import com.example.luxestyle.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user/addresses")
public class UserAddressController {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public UserAddressController(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String viewAddresses(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            User user = userRepository.findByUsername(username).orElse(null);
            if (user != null) {
                List<Address> addresses = addressRepository.findByUser(user);
                model.addAttribute("addresses", addresses);
                model.addAttribute("newAddress", new Address());
                model.addAttribute("states", State.values());
                return "user/address/list";
            }
        }
        return "redirect:/login";
    }

    @PostMapping("/add")
    public String addAddress(@ModelAttribute Address address, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            User user = userRepository.findByUsername(username).orElse(null);
            if (user != null) {
                address.setUser(user);
                addressRepository.save(address);
            }
        }
        return "redirect:/user/addresses";
    }

    @GetMapping("/delete/{id}")
    public String deleteAddress(@PathVariable Long id, Principal principal) {
        if (principal != null) {
            Address address = addressRepository.findById(id).orElse(null);
            if (address != null && address.getUser().getUsername().equals(principal.getName())) {
                addressRepository.delete(address);
            }
        }
        return "redirect:/user/addresses";
    }
}
