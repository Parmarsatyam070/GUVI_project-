package com.frauddetection.controller;

import com.frauddetection.model.Transaction;
import com.frauddetection.service.TransactionService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/transaction")
public class TransactionServlet extends HttpServlet {

    private final TransactionService service = new TransactionService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("transactions", service.getAllTransactions());
        req.getRequestDispatcher("/views/transaction.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // expected params: userId, amount, age, paymentType
        int userId = Integer.parseInt(req.getParameter("userId"));
        double amount = Double.parseDouble(req.getParameter("amount"));
        int age = Integer.parseInt(req.getParameter("age"));
        String paymentType = req.getParameter("paymentType");

        Transaction t = new Transaction(userId, amount, age, paymentType);
        // fraudScore left as 0 for now; FraudCheckServlet will analyze and save alerts
        int id = service.insertTransaction(t);
        resp.sendRedirect(req.getContextPath() + "/transaction");
    }
}
