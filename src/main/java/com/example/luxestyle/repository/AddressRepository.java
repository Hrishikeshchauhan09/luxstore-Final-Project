package com.example.luxestyle.repository;

import com.example.luxestyle.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.luxestyle.model.User;
import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUser(User user);
}
