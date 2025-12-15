package com.frauddetection.model;

public class FraudAlert {
    private int id;
    private int transactionId;
    private boolean fraud;
    private double score;

    public FraudAlert() {}

    public FraudAlert(int transactionId, boolean fraud, double score) {
        this.transactionId = transactionId;
        this.fraud = fraud;
        this.score = score;
    }

    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getTransactionId() { return transactionId; }
    public void setTransactionId(int transactionId) { this.transactionId = transactionId; }

    public boolean isFraud() { return fraud; }
    public void setFraud(boolean fraud) { this.fraud = fraud; }

    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }
}