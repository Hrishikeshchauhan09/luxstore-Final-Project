package com.example.luxestyle.repository;

import com.example.luxestyle.model.Product;
import com.example.luxestyle.model.enums.Category;
import com.example.luxestyle.model.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);

    List<Product> findByGender(Gender gender);

    List<Product> findByCategoryAndGender(Category category, Gender gender);

    List<Product> findByNameContainingIgnoreCase(String keyword);

    List<Product> findByDescriptionContainingIgnoreCase(String keyword);

    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
}
