package com.example.luxestyle.model;

import com.example.luxestyle.model.enums.Category;
import com.example.luxestyle.model.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String smallDescription;

    @Lob
    private String description;

    private Double price;

    private Integer sellingPrice;
    private Integer discountedPrice;

    @Lob
    private byte[] image;
}
