# 🔧 Quick Fix - Application Run Issue

## ✅ Issue Fixed: Duplicate /admin Mapping

**Problem:** Application crash ho raha tha kyunki `/admin` endpoint duplicate tha.

**Solution:** `OrderController.java` mein `/admin` ko `/admin/orders` mein change kar diya.

---

## 🚀 How to Run Application

### Method 1: Using Maven (Recommended)

```bash
cd c:\Users\Dinesh\Downloads\luxestore-ps2-main\luxestore-ps2-main
./mvnw spring-boot:run
```

### Method 2: Using IDE
1. Open `LuxestyleApplication.java`
2. Right-click → Run 'LuxestyleApplication'

---

## 📋 Pre-Run Checklist

### 1. **MySQL Running hai?**
```bash
# Check MySQL service
Get-Service MySQL* | Select-Object Name, Status
```

**Agar nahi chal raha:**
- XAMPP start karo
- Ya MySQL service start karo

### 2. **Database Exists?**
```sql
CREATE DATABASE IF NOT EXISTS luxestyle;
```

### 3. **Password Correct hai?**
Check `application.properties`:
```properties
spring.datasource.password=root  # Apna password dalo
```

### 4. **Port Available hai?**
```properties
server.port=8080  # Ya koi aur free port
```

---

## 🐛 Common Errors & Solutions

### Error 1: Port Already in Use
```
Web server failed to start. Port 8080 was already in use.
```

**Solution:**
```properties
# application.properties mein change karo:
server.port=8081
```

### Error 2: MySQL Connection Failed
```
Communications link failure
```

**Solution:**
1. MySQL service start karo
2. Password check karo
3. Database create karo: `CREATE DATABASE luxestyle;`

### Error 3: Duplicate Mapping
```
Ambiguous mapping. Cannot map 'adminController' method
```

**Solution:** ✅ Already Fixed!
- Changed `/admin` to `/admin/orders` in OrderController

---

## ✅ What Was Fixed

### File: `OrderController.java`

**Before:**
```java
@GetMapping("/admin")  // ❌ Conflict with AdminController
public String viewAllOrders(Model model) {
    model.addAttribute("orders", orderRepository.findAll());
    return "admin/order/view_order";
}
```

**After:**
```java
@GetMapping("/admin/orders")  // ✅ No conflict
public String viewAllOrders(Model model) {
    model.addAttribute("orders", orderRepository.findAll());
    return "admin/order/view_order";
}
```

---

## 🎯 Access URLs After Start

| Page | URL |
|------|-----|
| Home | http://localhost:8080/ |
| Products | http://localhost:8080/products |
| About | http://localhost:8080/about |
| Contact | http://localhost:8080/contact |
| Login | http://localhost:8080/login |
| Admin Dashboard | http://localhost:8080/admin/dashboard |
| Admin Orders | http://localhost:8080/admin/orders |

---

## 📝 Step-by-Step Run Guide

### Step 1: Start MySQL
```bash
# XAMPP Control Panel → Start MySQL
# OR
net start MySQL80  # Windows service
```

### Step 2: Verify Database
```sql
mysql -u root -p
SHOW DATABASES;
-- Should show 'luxestyle'
```

### Step 3: Run Application
```bash
cd c:\Users\Dinesh\Downloads\luxestore-ps2-main\luxestore-ps2-main
./mvnw clean spring-boot:run
```

### Step 4: Wait for Success Message
```
Tomcat started on port(s): 8080 (http)
Started LuxestyleApplication in X.XXX seconds
```

### Step 5: Open Browser
```
http://localhost:8080
```

---

## 🔍 Debug Commands

### Check if Application is Running
```bash
netstat -ano | findstr :8080
```

### View Recent Logs
```bash
Get-Content app.log -Tail 50
```

### Kill Process on Port 8080
```bash
# Find PID
netstat -ano | findstr :8080

# Kill process (replace PID)
taskkill /PID <PID> /F
```

---

## 💡 Tips

1. **Clean Build:**
   ```bash
   ./mvnw clean install
   ```

2. **Skip Tests:**
   ```bash
   ./mvnw spring-boot:run -DskipTests
   ```

3. **Debug Mode:**
   ```bash
   ./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug"
   ```

---

## 🎉 Success Indicators

Application successfully start hone ke baad ye dikhega:

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v4.0.2)

...
Tomcat started on port(s): 8080 (http)
Started LuxestyleApplication in 15.234 seconds
```

---

## 📞 Still Having Issues?

Agar phir bhi run nahi ho raha, toh ye check karo:

1. ✅ MySQL running hai?
2. ✅ Database `luxestyle` exists?
3. ✅ Password correct hai?
4. ✅ Port 8080 free hai?
5. ✅ Java 17+ installed hai?

---

**Last Updated:** 2026-02-10  
**Status:** ✅ Duplicate mapping issue fixed
