package com.example.luxestyle.repository;

import com.example.luxestyle.model.Cart;
import com.example.luxestyle.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUser(User user);

    Cart findByUserAndProduct(User user, com.example.luxestyle.model.Product product);

    void deleteByUser(User user);
}
