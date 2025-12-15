package com.frauddetection.controller;

import com.frauddetection.model.Transaction;
import com.frauddetection.service.TransactionService;
import com.frauddetection.service.FraudDetectionService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/fraud/check")
public class FraudCheckServlet extends HttpServlet {

    private final TransactionService txService = new TransactionService();
    private final FraudDetectionService fraudService = new FraudDetectionService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get parameters, create transaction object (or find existing by id)
        int userId = Integer.parseInt(req.getParameter("userId"));
        double amount = Double.parseDouble(req.getParameter("amount"));
        int age = Integer.parseInt(req.getParameter("age"));
        String paymentType = req.getParameter("paymentType");

        Transaction tx = new Transaction(userId, amount, age, paymentType);
        // store transaction first (fraudScore will be updated if needed)
        int txId = txService.insertTransaction(tx);
        tx.setId(txId);

        double score = fraudService.analyzeAndStore(tx); // sets tx.fraudScore and saves alert
        // optionally update transaction record with fraud score (not implemented above)
        req.setAttribute("wekaScore", score); // we averaged inside service; if you want both scores expose them
        req.setAttribute("finalScore", score);
        req.setAttribute("transaction", tx);

        req.getRequestDispatcher("/views/fraud-result.jsp").forward(req, resp);
    }
}