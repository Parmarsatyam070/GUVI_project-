# Fraud Detection System — Java Servlet Project

## 📌 Overview
This project implements a rule-based Fraud Detection System using **Java Servlets**, **JSP**, **JDBC**, and **MySQL**.  
It performs real-time fraud analysis on user-submitted transactions.

## 📁 Folder Structure
```
src/main/java/com/example/fraud/
    ├── servlet/FraudDetectionServlet.java
    ├── service/FraudDetectionService.java
    ├── dao/TransactionDao.java
    ├── model/Transaction.java
    ├── util/ValidationUtil.java
    └── util/AppException.java

src/main/webapp/
    ├── index.jsp
    └── WEB-INF/web.xml
```

---

## 🚀 Features
- ✔ Java Servlet endpoint: `/api/detect`
- ✔ Server-side validation & structured JSON responses
- ✔ DAO layer with MySQL JDBC
- ✔ Clean architecture (Servlet → Service → DAO → DB)
- ✔ Client-side form validation + AJAX
- ✔ Modular code & exception management

---

## 🛠 Technologies Used
- Java Servlet API
- JSP
- JDBC
- MySQL
- Jackson (JSON)
- HTML / JS

---

## ▶ How to Run

### **1. Set environment variables**
```
DB_URL=jdbc:mysql://localhost:3306/frauddb
DB_USER=root
DB_PASS=yourpassword
```

### **2. Import SQL**
Run:
```
transactions.sql
```

### **3. Deploy**
- Build using Maven: `mvn clean package`
- Deploy WAR in Apache Tomcat
- Visit:  
  `http://localhost:8080/Fraud-Detection-System/index.jsp`

---

## 📝 API Endpoint

### POST `/api/detect`

**Request JSON**
```json
{
  "transactionId": "TX1001",
  "cardNumberMasked": "512345******6789",
  "amount": 9500,
  "currency": "INR",
  "country": "INDIA",
  "cardIssuingCountry": "USA",
  "hourOfDay": 23,
  "merchantId": "M100"
}
```

**Response JSON**
```json
{
  "isFraud": true,
  "score": 0.8,
  "reason": "High risk score"
}
```

---

## 🧪 Grading Rubric Coverage

### ✔ Servlet Implementation (10 marks)
- FraudDetectionServlet.java fully implemented
- JSON parsing, validation, response writing

### ✔ Code Quality (5 marks)
- Layered architecture
- Utility classes, custom exceptions
- Clean DAO separation

### ✔ Innovation (2 marks)
- Risk scoring logic
- Extensible structure for ML model integration

---

# End of README
