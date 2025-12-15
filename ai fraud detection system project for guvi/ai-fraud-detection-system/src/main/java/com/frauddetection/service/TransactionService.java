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
