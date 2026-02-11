# Luxestore-p2 Setup Guide

## Quick Start - Database Setup

### Step 1: Create MySQL Database

Open MySQL Command Line or MySQL Workbench and run:

```sql
CREATE DATABASE luxestyle;
```

### Step 2: Verify Database Configuration

Check `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/luxestyle
spring.datasource.username=root
spring.datasource.password=root
```

**IMPORTANT**: Change `root` to your actual MySQL username and password!

### Step 3: Run the Application

```bash
mvn spring-boot:run
```

### Step 4: Access the Application

Open your browser and go to: **http://localhost:8081**

---

## Creating Admin User

### Step 1: Register a User

1. Go to http://localhost:8081/register
2. Enter username and password
3. Click Register

### Step 2: Make User an Admin

Open MySQL and run:

```sql
USE luxestyle;
UPDATE users SET role = 'ADMIN' WHERE username = 'your_username';
```

Replace `your_username` with the username you registered.

### Step 3: Login as Admin

1. Go to http://localhost:8081/login
2. Login with your username and password
3. Access admin panel at http://localhost:8081/admin

---

## Troubleshooting

### Error: "Unable to connect to database"

**Solution**: 
1. Make sure MySQL is running
2. Verify database `luxestyle` exists
3. Check username/password in `application.properties`

### Error: "Port 8080 was already in use"

**Solution**:
We have changed the default port to **8081** in `application.properties` to avoid this issue.
If 8081 is also busy, change `server.port=8082` in `src/main/resources/application.properties`.

### Error: "Ambiguous mapping /admin"

**Solution**:
This has been fixed by removing the conflicting method in `SecurityController`. The `/admin` path is now exclusively handled by `AdminController`.

### Application not starting

**Solution**:
1. Check if port 8081 is free
2. Verify MySQL is running and accessible
3. Check application logs for specific errors

---

## Features Available

✅ User Registration & Login (username-based)
✅ Product Management (Admin)
✅ Order Management
✅ Shopping Cart
✅ Address Management (Admin)
✅ Role-based Access Control

---

## Need Help?

Check the full README.md for detailed documentation!
