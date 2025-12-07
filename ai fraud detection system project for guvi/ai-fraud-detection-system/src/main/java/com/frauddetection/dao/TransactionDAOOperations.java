package com.frauddetection.dao;

import java.util.List;
import java.sql.SQLException;
import com.frauddetection.model.Transaction;

public interface TransactionDAOOperations {

    // Insert a transaction
    boolean save(Transaction transaction) throws SQLException;

    // Retrieve all transactions
    List<Transaction> getAllTransactions() throws SQLException;
}
