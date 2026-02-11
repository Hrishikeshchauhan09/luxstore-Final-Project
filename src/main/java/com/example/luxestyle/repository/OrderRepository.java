package com.example.luxestyle.repository;

import com.example.luxestyle.model.Order;
import com.example.luxestyle.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);

    List<Order> findByUserOrderByOrderDateDesc(User user);
}
