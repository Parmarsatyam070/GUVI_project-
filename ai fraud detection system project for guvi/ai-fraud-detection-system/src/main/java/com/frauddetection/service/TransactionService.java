/*package com.frauddetection.service;

import com.frauddetection.model.Transaction;
import com.frauddetection.config.DatabaseConfig;
import com.frauddetection.exception.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for transaction DB operations.
 */
/*public class TransactionService implements TransactionServiceOperations {

    private final List<Transaction> cache = new ArrayList<>();

    /**
     * Fetch all transactions from DB.
     */
   /* @Override
    public List<Transaction> getAll() throws DatabaseException {
        cache.clear();

        String sql = "SELECT transaction_id, user_id, amount, merchant FROM transactions";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Transaction t = new Transaction(
                        rs.getString("transaction_id"),
                        rs.getString("user_id"),
                        rs.getDouble("amount"),
                        rs.getString("merchant")
                );
                cache.add(t);
            }

            return cache;

        } catch (SQLException e) {
            throw new DatabaseException("Failed to fetch transactions!", e);
        }
    }

    /**
     * Saves a transaction to DB.
     */
   /* @Override
    public boolean save(Transaction t) {
        String sql = "INSERT INTO transactions (transaction_id, user_id, amount, merchant) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, t.getTransactionId());
            ps.setString(2, t.getUserId());
            ps.setDouble(3, t.getAmount());
            ps.setString(4, t.getMerchant());

            ps.executeUpdate();
            cache.add(t);

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}*/


package com.frauddetection.service;

import com.frauddetection.dao.TransactionDAO;
import com.frauddetection.model.Transaction;

import java.util.List;

public class TransactionService {

    private final TransactionDAO dao = new TransactionDAO();

    public int insertTransaction(Transaction t) {
        return dao.insertTransaction(t);
    }

    public List<Transaction> getAllTransactions() {
        return dao.getAllTransactions();
    }

    public Transaction getTransaction(int id) {
        return dao.getById(id);
    }
}
