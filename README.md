# OpenCart Automation Testing Project

## 📌 Overview
This project demonstrates **automation testing** of the [OpenCart Demo](https://demo.opencart.com/) using **Java, Selenium WebDriver, and TestNG**. The automation suite covers key e-commerce functionalities like **user authentication, product search, cart management, and checkout process**.

## 📂 Repository Contents
- **`src/tests/`** – Contains Selenium WebDriver test cases.
- **`bin/tests/`** – Compiled test class files.
- **`test-output/`** – TestNG-generated reports.
- **`.settings/`** – IDE-related configurations.
- **`.classpath` & `.project`** – Eclipse project metadata.

## 🛠 Tech Stack
- **Java** – Programming language for test scripts.
- **Selenium WebDriver** – Browser automation.
- **TestNG** – Test execution framework.
- **Maven** – Dependency and build management.
- **Extent Reports** – Test reporting.

## 📝 Test Coverage
### **1️⃣ User Authentication**
- ✅ Login with valid credentials
- ❌ Invalid login attempt (Negative Test)
- 🔄 Logout functionality

### **2️⃣ Product Search & Navigation**
- 🔍 Search for a product
- 📂 Browse product categories
- 📄 Validate product details page

### **3️⃣ Shopping Cart Management**
- ➕ Add a product to the cart
- ❌ Remove a product from the cart
- 🏷 Apply a coupon (if available)

### **4️⃣ Checkout Process**
- 🛒 Checkout as a registered user
- 💳 Fill in billing & shipping details
- ✅ Complete an order

### **📊 Test Reports
TestNG Reports – HTML reports for execution results.

Extent Reports – Visual test reports with screenshots.

### **🎯 Key Learnings
-Implementing Selenium WebDriver with TestNG.
-Structuring tests using the Page Object Model (POM).
-Handling dynamic elements, waits, and browser interactions.
-Running parallel tests with TestNG XML.
-Generating detailed Extent Reports.
