/*package com.frauddetection.model;

/**
 * FraudAlert model used by UI and Service layers.
 * Includes basic fields + getters + setters.
 */
/*public class FraudAlert {

    private String alertId;
    private String transactionId;
    private String userId;
    private String reason;
    private String severity;

    // Default constructor (required for JavaFX & Jackson)
    public FraudAlert() {
    }

    // Optional full constructor
    public FraudAlert(String alertId, String transactionId, String userId, String reason, String severity) {
        this.alertId = alertId;
        this.transactionId = transactionId;
        this.userId = userId;
        this.reason = reason;
        this.severity = severity;
    }

    public String getAlertId() {
        return alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }
}
*/



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





/*package com.frauddetection.model;

public class FraudAlert {

    private int id;
    private int transactionId;
    private boolean fraud;

    public FraudAlert(int txId, boolean fraud) {
        this.transactionId = txId;
        this.fraud = fraud;
    }

    // getters & setters
}
*/