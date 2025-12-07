package com.frauddetection.service;

import com.frauddetection.model.Transaction;
import com.frauddetection.exception.DatabaseException;

import java.util.List;

public interface TransactionServiceOperations {

    // fetch all transactions
    List<Transaction> getAll() throws DatabaseException;

    // save a transaction
    boolean save(Transaction transaction);
}
