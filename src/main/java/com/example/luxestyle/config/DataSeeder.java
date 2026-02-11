package com.example.luxestyle.config;

import com.example.luxestyle.model.Product;
import com.example.luxestyle.model.enums.Category;
import com.example.luxestyle.model.enums.Gender;
import com.example.luxestyle.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;

    public DataSeeder(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Only seed data if the database is empty
        if (productRepository.count() == 0) {
            System.out.println("Seeding database with premium products...");
            // productRepository.deleteAll(); // Removed to prevent data loss

            List<ProductSpec> catalog = Arrays.asList(
                    // RINGS
                    new ProductSpec("Royal Sapphire Halo Ring", Category.RINGS, Gender.WOMEN, 1299.00,
                            "A magnificent 2-carat oval sapphire surrounded by a halo of sparkling diamonds set in 18k white gold. A timeless symbol of elegance.",
                            "https://images.unsplash.com/photo-1605100804763-ebea2406a95f?q=80&w=1000&auto=format&fit=crop"),

                    new ProductSpec("Men's Signet Onyx Ring", Category.RINGS, Gender.MEN, 149.00,
                            "Classic signet ring featuring a flat black onyx stone set in sterling silver. A bold statement of authority and style.",
                            "https://images.unsplash.com/photo-1622398925373-3f91b1e275f5?q=80&w=1000&auto=format&fit=crop"),

                    // CHAINS
                    new ProductSpec("14k Gold Rope Chain", Category.CHAINS, Gender.UNISEX, 899.00,
                            "Diamond-cut rope chain crafted from solid 14k yellow gold. Radiates a brilliant shine and offers durability for daily wear.",
                            "https://images.unsplash.com/photo-1611591437281-460bfbe1220a?q=80&w=1000&auto=format&fit=crop"),

                    new ProductSpec("Sterling Silver Curb Chain", Category.CHAINS, Gender.MEN, 129.00,
                            "Heavy sterling silver curb link chain with a polished finish. Features a secure lobster clasp. Perfect for a masculine look.",
                            "https://images.unsplash.com/photo-1573408301185-9146fe634ad0?q=80&w=1000&auto=format&fit=crop"),

                    // BRACELETS
                    new ProductSpec("Diamond Tennis Bracelet", Category.BRACELETS, Gender.WOMEN, 2499.00,
                            "Exquisite tennis bracelet featuring 3 carats of round-cut diamonds set in platinum. A luxurious addition to any jewelry collection.",
                            "https://images.unsplash.com/photo-1515562141207-7a88fb7ce338?q=80&w=1000&auto=format&fit=crop"),

                    new ProductSpec("Leather Anchor Bracelet", Category.BRACELETS, Gender.MEN, 55.00,
                            "Braided premium leather bracelet with a marine-grade stainless steel anchor clasp. Rugged yet sophisticated.",
                            "https://images.unsplash.com/photo-1611591437281-460bfbe1220a?q=80&w=1000&auto=format&fit=crop"), // Reused
                                                                                                                              // generic
                                                                                                                              // jewelry
                                                                                                                              // placeholder
                                                                                                                              // if
                                                                                                                              // specific
                                                                                                                              // not
                                                                                                                              // found,
                                                                                                                              // but
                                                                                                                              // trying
                                                                                                                              // specific

                    // WATCHES
                    new ProductSpec("Chronoswiss Automatic", Category.WATCHES, Gender.MEN, 3450.00,
                            "Swiss-made automatic movement watch with a sapphire crystal face, leather strap, and intricate chronograph dials.",
                            "https://images.unsplash.com/photo-1522312346375-d1a52e2b99b3?q=80&w=1000&auto=format&fit=crop"),

                    new ProductSpec("Rose Gold Minimalist", Category.WATCHES, Gender.WOMEN, 199.00,
                            "Sleek rose gold mesh strap watch with a pearl white dial. Minimalist design suitable for both casual and formal occasions.",
                            "https://images.unsplash.com/photo-1542496658-e33a6d0d9aa5?q=80&w=1000&auto=format&fit=crop"),

                    // PERFUMES
                    new ProductSpec("Noir Intense Eau de Parfum", Category.PERFUMES, Gender.MEN, 115.00,
                            "A deep, woody fragrance with notes of cedar, bergamot, and black pepper. Designed for the modern gentleman.",
                            "https://images.unsplash.com/photo-1594035910387-fea4779426fa?q=80&w=1000&auto=format&fit=crop"),

                    new ProductSpec("Blooming Peony Scent", Category.PERFUMES, Gender.WOMEN, 95.00,
                            "Fresh and floral, capturing the essence of blooming peonies and red apples with a soft suede finish.",
                            "https://images.unsplash.com/photo-1541643600914-78b084683601?q=80&w=1000&auto=format&fit=crop"),

                    // WALLETS
                    new ProductSpec("Genuine Alligator Wallet", Category.WALLETS, Gender.MEN, 299.00,
                            "Handcrafted from genuine alligator leather. Features 6 card slots and a lined bill compartment. Ultimate luxury.",
                            "https://images.unsplash.com/photo-1627123424574-183ce3021aeb?q=80&w=1000&auto=format&fit=crop"),

                    // BELTS
                    new ProductSpec("Classic Reversible Leather Belt", Category.BELTS, Gender.MEN, 85.00,
                            "Full-grain leather belt reversible from black to brown with a brushed metallic buckle. Versatile for business and casual.",
                            "https://images.unsplash.com/photo-1624222247344-550fb60583dc?q=80&w=1000&auto=format&fit=crop"));

            for (ProductSpec spec : catalog) {
                createProduct(spec);
            }

            System.out.println("Database seeded with " + catalog.size() + " premium products!");
        }
    }

    private void createProduct(ProductSpec spec) {
        try {
            Product product = new Product();
            product.setName(spec.name);
            product.setCategory(spec.category);
            product.setGender(spec.gender);
            product.setPrice(spec.price);
            product.setDescription(spec.description);

            try (InputStream in = new URL(spec.imageUrl).openStream()) {
                byte[] imageBytes = in.readAllBytes();
                product.setImage(imageBytes);
            }

            productRepository.save(product);
            System.out.println("Added: " + spec.name);
        } catch (Exception e) {
            System.err.println("Failed to add " + spec.name + ": " + e.getMessage());
        }
    }

    // Helper record
    private static class ProductSpec {
        String name;
        Category category;
        Gender gender;
        Double price;
        String description;
        String imageUrl;

        public ProductSpec(String name, Category category, Gender gender, Double price, String description,
                String imageUrl) {
            this.name = name;
            this.category = category;
            this.gender = gender;
            this.price = price;
            this.description = description;
            this.imageUrl = imageUrl;
        }
    }
}
