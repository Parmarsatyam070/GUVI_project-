package com.frauddetection.dao;

import com.frauddetection.model.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO extends BaseDAO {

    public int insertTransaction(Transaction t) {
        String sql = "INSERT INTO transactions(user_id, amount, age, payment_type, timestamp, fraud_score) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, t.getUserId());
            ps.setDouble(2, t.getAmount());
            ps.setInt(3, t.getAge());
            ps.setString(4, t.getPaymentType());
            ps.setTimestamp(5, t.getTimestamp());
            ps.setDouble(6, t.getFraudScore());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
            return -1;
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting transaction", e);
        }
    }

    public List<Transaction> getAllTransactions() {
        String sql = "SELECT id, user_id, amount, age, payment_type, timestamp, fraud_score FROM transactions ORDER BY timestamp DESC";
        List<Transaction> out = new ArrayList<>();
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Transaction t = new Transaction(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getDouble("amount"),
                        rs.getInt("age"),
                        rs.getString("payment_type"),
                        rs.getTimestamp("timestamp"),
                        rs.getDouble("fraud_score")
                );
                out.add(t);
            }
            return out;
        } catch (SQLException e) {
            throw new RuntimeException("Error reading transactions", e);
        }
    }

    public Transaction getById(int id) {
        String sql = "SELECT id, user_id, amount, age, payment_type, timestamp, fraud_score FROM transactions WHERE id = ?";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Transaction(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getDouble("amount"),
                            rs.getInt("age"),
                            rs.getString("payment_type"),
                            rs.getTimestamp("timestamp"),
                            rs.getDouble("fraud_score")
                    );
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading transaction by id", e);
        }
    }
}