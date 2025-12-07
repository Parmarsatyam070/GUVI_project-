package com.frauddetection.model;

public class FraudResult {

    private int transactionId;
    private boolean fraudulent;
    private double confidenceScore;

    public FraudResult(int transactionId, boolean fraudulent, double confidenceScore) {
        this.transactionId = transactionId;
        this.fraudulent = fraudulent;
        this.confidenceScore = confidenceScore;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public boolean isFraudulent() {
        return fraudulent;
    }

    public double getConfidenceScore() {
        return confidenceScore;
    }
}
