package com.frauddetection.dao;

import com.frauddetection.model.FraudAlert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FraudDAO extends BaseDAO {

    public void save(FraudAlert alert) {
        String sql = "INSERT INTO fraud_alerts(transaction_id, is_fraud, score) VALUES (?, ?, ?)";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, alert.getTransactionId());
            ps.setBoolean(2, alert.isFraud());
            ps.setDouble(3, alert.getScore());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving fraud alert", e);
        }
    }
}
