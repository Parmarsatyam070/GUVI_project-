/*package com.frauddetection.model;

public class Transaction {

    private String transactionId;
    private String userId;
    private double amount;
    private String merchant;

    // Default constructor (required for UI screens)
    public Transaction() {
    }

    // Full constructor
    public Transaction(String transactionId, String userId, double amount, String merchant) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.amount = amount;
        this.merchant = merchant;
    }

    // Getters
    public String getTransactionId() {
        return transactionId;
    }

    public String getUserId() {
        return userId;
    }

    public double getAmount() {
        return amount;
    }

    public String getMerchant() {
        return merchant;
    }

    // Setters (these fix your TransactionWindow errors)
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }
}*/




package com.frauddetection.model;

import java.sql.Timestamp;

public class Transaction {
    private int id;
    private int userId;
    private double amount;
    private int age;
    private String paymentType;
    private Timestamp timestamp;
    private double fraudScore;

    public Transaction() {}

    // Convenient constructor used by DAOs/services
    public Transaction(int id, int userId, double amount, int age, String paymentType, Timestamp timestamp, double fraudScore) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.age = age;
        this.paymentType = paymentType;
        this.timestamp = timestamp;
        this.fraudScore = fraudScore;
    }

    public Transaction(int userId, double amount, int age, String paymentType) {
        this.userId = userId;
        this.amount = amount;
        this.age = age;
        this.paymentType = paymentType;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getPaymentType() { return paymentType; }
    public void setPaymentType(String paymentType) { this.paymentType = paymentType; }

    public Timestamp getTimestamp() { return timestamp; }
    public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }

    public double getFraudScore() { return fraudScore; }
    public void setFraudScore(double fraudScore) { this.fraudScore = fraudScore; }
}




/*
package com.frauddetection.model;

import java.sql.Timestamp;

public class Transaction {

    private int id;
    private double amount;
    private String location;
    private Timestamp timestamp;

    public Transaction(double amount, String location) {
        this.amount = amount;
        this.location = location;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    // getters & setters
}
*/