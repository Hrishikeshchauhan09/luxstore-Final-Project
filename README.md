# Luxestore-p2 - E-Commerce Application

A full-featured e-commerce web application built with **Spring Boot**, **Thymeleaf**, **MySQL**, and **Spring Security**. This application provides comprehensive product management, user authentication, shopping cart functionality, order processing, and address management.

## 📋 Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Database Configuration](#database-configuration)
- [Running the Application](#running-the-application)
- [Default Admin Access](#default-admin-access)
- [Project Structure](#project-structure)
- [API Endpoints](#api-endpoints)
- [Usage Guide](#usage-guide)
- [Contributing](#contributing)
- [License](#license)

## ✨ Features

### User Features
- **User Registration & Authentication**: Secure username-based login with BCrypt password encryption
- **Product Browsing**: View products by category and gender
- **Shopping Cart**: Add, update, and remove items from cart
- **Order Management**: Place orders and track order history
- **User Profile**: Manage personal information

### Admin Features
- **Product Management**: Full CRUD operations for products
  - Add new products with images
  - Update existing products
  - Delete products
  - View all products
- **Order Management**: View and manage customer orders
- **Address Management**: Manage user delivery addresses
  - Add new addresses
  - Update existing addresses
  - Delete addresses
  - View all addresses with user associations

### Security Features
- Role-based access control (USER and ADMIN roles)
- Protected admin routes
- Password encryption with BCrypt
- Session management
- CSRF protection

## 🛠 Technologies Used

- **Backend Framework**: Spring Boot 4.0.2
- **Template Engine**: Thymeleaf with Layout Dialect
- **Database**: MySQL
- **ORM**: Spring Data JPA (Hibernate)
- **Security**: Spring Security 6
- **Build Tool**: Maven
- **Java Version**: 17
- **Additional Libraries**:
  - Lombok (for reducing boilerplate code)
  - Spring Boot DevTools (for development)
  - Thymeleaf Extras Spring Security (for security integration in templates)

## 📦 Prerequisites

Before running this application, ensure you have the following installed:

- **Java Development Kit (JDK) 17** or higher
- **Maven 3.6+**
- **MySQL 8.0+**
- **Git** (for cloning the repository)

## 🚀 Installation & Setup

### 1. Clone the Repository

```bash
git clone <repository-url>
cd Luxestore-p2
```

### 2. Create MySQL Database

Open MySQL command line or MySQL Workbench and create a new database:

```sql
CREATE DATABASE luxestyle;
```

### 3. Configure Database Connection

Open `src/main/resources/application.properties` and update the database credentials if needed:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/luxestyle
spring.datasource.username=root
spring.datasource.password=root
```

**Note**: Change `root` to your MySQL username and password if different.

### 4. Build the Project

```bash
mvn clean install
```

This will download all dependencies and compile the project.

## 💾 Database Configuration

The application uses **Hibernate's auto-DDL** feature to automatically create database tables on startup:

```properties
spring.jpa.hibernate.ddl-auto=update
```

### Database Tables Created

The following tables will be automatically created:

- `users` - User accounts with authentication details
- `product` - Product catalog
- `cart` - Shopping cart items
- `orders` - Customer orders
- `address` - User delivery addresses

### Enums Used

- **Category**: Product categories (e.g., CLOTHING, ACCESSORIES, FOOTWEAR, etc.)
- **Gender**: Product gender classification (MALE, FEMALE, UNISEX)
- **OrderStatus**: Order processing status (PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELLED)
- **State**: Indian states and union territories (AP, AR, AS, BR, CT, etc.)

## ▶️ Running the Application

### Using Maven

```bash
mvn spring-boot:run
```

### Using Java

```bash
mvn clean package
java -jar target/Luxestore-p2-0.0.1-SNAPSHOT.jar
```

The application will start on **http://localhost:8081**

## 🔐 Default Admin Access

To access admin features, you need to manually create an admin user in the database:

### Step 1: Register a User

1. Navigate to **http://localhost:8081/register**
2. Create a new account with username and password

### Step 2: Update User Role to ADMIN

Open MySQL and run:

```sql
UPDATE users SET role = 'ADMIN' WHERE username = 'your_username';
```

Replace `your_username` with the username you registered.

### Step 3: Login as Admin

1. Navigate to **http://localhost:8081/login**
2. Login with your username and password
3. Access admin panel at **http://localhost:8081/admin**

## 📁 Project Structure

```
Luxestore-p2/
├── src/
│   ├── main/
│   │   ├── java/com/example/luxestyle/
│   │   │   ├── config/
│   │   │   │   ├── DataSeeder.java           # Initial data seeding
│   │   │   │   └── WebSecurityConfig.java    # Security configuration
│   │   │   ├── controller/
│   │   │   │   ├── AdminController.java       # Admin dashboard
│   │   │   │   ├── AddressController.java     # Address management
│   │   │   │   ├── CartController.java        # Shopping cart
│   │   │   │   ├── CoreController.java        # Core routes
│   │   │   │   ├── OrderController.java       # Order management
│   │   │   │   ├── ProductController.java     # Product CRUD
│   │   │   │   ├── PublicProductController.java # Public product views
│   │   │   │   ├── SecurityController.java    # Auth & registration
│   │   │   │   └── UserController.java        # User management
│   │   │   ├── model/
│   │   │   │   ├── Address.java               # Address entity
│   │   │   │   ├── Cart.java                  # Cart entity
│   │   │   │   ├── Order.java                 # Order entity
│   │   │   │   ├── Product.java               # Product entity
│   │   │   │   ├── User.java                  # User entity
│   │   │   │   └── enums/
│   │   │   │       ├── Category.java          # Product categories
│   │   │   │       ├── Gender.java            # Gender classification
│   │   │   │       ├── OrderStatus.java       # Order statuses
│   │   │   │       └── State.java             # Indian states
│   │   │   ├── repository/
│   │   │   │   ├── AddressRepository.java
│   │   │   │   ├── CartRepository.java
│   │   │   │   ├── OrderRepository.java
│   │   │   │   ├── ProductRepository.java
│   │   │   │   └── UserRepository.java
│   │   │   └── LuxestyleApplication.java      # Main application
│   │   └── resources/
│   │       ├── templates/
│   │       │   ├── admin/
│   │       │   │   ├── address/               # Address management views
│   │       │   │   ├── order/                 # Order management views
│   │       │   │   ├── product/               # Product management views
│   │       │   │   └── dashboard.html         # Admin dashboard
│   │       │   ├── fragments/
│   │       │   │   ├── footer.html            # Footer fragment
│   │       │   │   └── navbar.html            # Navbar fragment
│   │       │   ├── base.html                  # Base layout template
│   │       │   ├── cart.html                  # Shopping cart
│   │       │   ├── index.html                 # Homepage
│   │       │   ├── login.html                 # Login page
│   │       │   ├── orders.html                # User orders
│   │       │   ├── products.html              # Product listing
│   │       │   └── register.html              # Registration page
│   │       ├── static/
│   │       │   ├── css/                       # Stylesheets
│   │       │   ├── js/                        # JavaScript files
│   │       │   └── images/                    # Image assets
│   │       └── application.properties         # Application configuration
├── pom.xml                                    # Maven dependencies
└── README.md                                  # This file
```

## 🌐 API Endpoints

### Public Routes

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/` | Homepage |
| GET | `/login` | Login page |
| POST | `/login` | Process login |
| GET | `/register` | Registration page |
| POST | `/register` | Process registration |
| GET | `/products` | View all products |
| GET | `/products/{id}` | View product details |

### User Routes (Authenticated)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/cart` | View shopping cart |
| POST | `/cart/add/{productId}` | Add item to cart |
| POST | `/cart/update/{id}` | Update cart item quantity |
| GET | `/cart/remove/{id}` | Remove item from cart |
| GET | `/orders` | View user orders |
| POST | `/orders/place` | Place new order |

### Admin Routes (ADMIN Role Required)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/admin` | Admin dashboard |
| GET | `/admin/products` | View all products |
| GET | `/admin/products/add` | Add product form |
| POST | `/admin/products/add` | Save new product |
| GET | `/admin/products/update/{id}` | Update product form |
| POST | `/admin/products/update` | Save product updates |
| GET | `/admin/products/delete/{id}` | Delete product |
| GET | `/admin/addresses` | View all addresses |
| GET | `/admin/addresses/add` | Add address form |
| POST | `/admin/addresses/add` | Save new address |
| GET | `/admin/addresses/update/{id}` | Update address form |
| POST | `/admin/addresses/update` | Save address updates |
| GET | `/admin/addresses/delete/{id}` | Delete address |
| GET | `/orders/admin` | View all orders (admin) |

## 📖 Usage Guide

### For Customers

1. **Browse Products**: Visit the homepage to see featured products
2. **Register**: Create an account using username and password
3. **Login**: Sign in with your credentials
4. **Shop**: Browse products and add items to your cart
5. **Checkout**: Review your cart and place an order
6. **Track Orders**: View your order history and status

### For Administrators

1. **Login as Admin**: Use admin credentials to access admin panel
2. **Manage Products**:
   - Add new products with name, category, gender, description, price, and image
   - Update existing products
   - Delete products from catalog
3. **Manage Orders**:
   - View all customer orders
   - Update order status
4. **Manage Addresses**:
   - View all user addresses
   - Add new addresses for users
   - Update or delete existing addresses

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- Thymeleaf team for the powerful template engine
- All contributors and users of this project

## 📞 Support

For issues, questions, or suggestions, please open an issue on the GitHub repository.

---

**Happy Shopping! 🛍️**
