# LuxeStyle - Premium Fashion E-Commerce Platform

LuxeStyle is a full-stack e-commerce web application built with **Spring Boot** and **Thymeleaf**. It provides a seamless shopping experience for users and a robust management system for administrators.

## 🚀 Features

### for Customers (Users)
*   **User Accounts**: Secure Registration and Login.
*   **Product Discovery**: Browse products by Category (Men/Women) and search by keywords.
*   **Shopping Cart**: Add items to cart, update quantities, and view totals.
*   **Checkout Process**: 
    *   Manage Shipping Addresses.
    *   Select Payment Method (Razorpay Integration available).
    *   Order Confirmation.
*   **Order History**: "My Orders" section to track past purchases.
*   **Profile Management**: Update personal details.

### for Administrators
*   **Product Management**: Add, specific detailed descriptions, uploading images, pricing, and stock management.
*   **Dashboard**: View and manage the entire product catalog.

---

## 🛠️ Technology Stack

*   **Backend Framework**: Java Spring Boot (v3.x/4.x)
*   **Frontend**: Thymeleaf (Template Engine), HTML5, CSS3, Bootstrap 5
*   **Database**: MySQL (using JPA/Hibernate for ORM)
*   **Security**: Spring Security (Authentication & Authorization)
*   **Build Tool**: Maven

---

## 🏗️ Architecture

This project follows the **Model-View-Controller (MVC)** architectural pattern:
*   **Model**: Java Entities (`Product`, `User`, `Cart`, `Order`) representing database tables.
*   **View**: Thymeleaf templates (`.html`) rendering the UI.
*   **Controller**: Spring classes handling HTTP requests and business logic.

---

## ⚙️ Setup & Installation

### Prerequisites
*   Java Development Kit (JDK) 17 or higher
*   Maven (or use `mvnw`)
*   MySQL Server

### Steps to Run
1.  **Database Configuration**:
    *   Ensure MySQL is running on port `3306`.
    *   Create a schema named `luxestyle_db`.
    *   Update `src/main/resources/application.properties` with your username/password.

2.  **Build the Project**:
    ```bash
    mvn clean install
    ```

3.  **Run the Application**:
    ```bash
    mvn spring-boot:run
    ```

4.  **Access the App**:
    *   Open browser: [http://localhost:8080](http://localhost:8080)
    *   **Admin Login** (Default): Create a user with role `ADMIN` in database purely for administrative access.

---

## 📸 Screenshots
*(Add screenshots of your Home Page, Product Page, and Checkout here)*

---

## 👨‍💻 Author
Developed by **Dinesh** for Final Year Project.
