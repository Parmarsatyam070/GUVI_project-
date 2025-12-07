/*package com.frauddetection.controller;

import com.frauddetection.model.Transaction;
import com.frauddetection.dao.TransactionDAO;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/transaction")
public class TransactionServlet extends HttpServlet {

    private final TransactionDAO dao = new TransactionDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        double amount = Double.parseDouble(req.getParameter("amount"));
        String location = req.getParameter("location");

        Transaction tx = new Transaction(amount, location);
        int id = dao.save(tx);

        req.setAttribute("txId", id);
        req.setAttribute("transaction", tx);

        req.getRequestDispatcher("/WEB-INF/views/transaction.jsp").forward(req, resp);
    }
}
*/






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








/*package com.frauddetection.controller;

import com.frauddetection.dao.TransactionDAO;
import com.frauddetection.model.Transaction;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/transaction")
public class TransactionServlet extends HttpServlet {

    private TransactionDAO dao = new TransactionDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Transaction t = new Transaction();
        t.setUserId(Integer.parseInt(req.getParameter("userId")));
        t.setAmount(Double.parseDouble(req.getParameter("amount")));
        t.setPaymentType(req.getParameter("paymentType"));

        dao.insertTransaction(t);

        resp.sendRedirect("transaction?list=true");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("transactions", dao.getAllTransactions());
        req.getRequestDispatcher("/views/transaction.jsp").forward(req, resp);
    }
}
*/