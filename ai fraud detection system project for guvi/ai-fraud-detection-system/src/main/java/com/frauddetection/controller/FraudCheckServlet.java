/*package com.frauddetection.controller;

import com.frauddetection.model.Transaction;
import com.frauddetection.service.FraudDetectionService;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/fraud-check")
public class FraudCheckServlet extends HttpServlet {

    private final FraudDetectionService service = new FraudDetectionService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        double amount = Double.parseDouble(req.getParameter("amount"));
        String location = req.getParameter("location");

        Transaction tx = new Transaction(amount, location);

        boolean fraud = service.isFraudulent(tx);

        req.setAttribute("fraud", fraud);
        req.getRequestDispatcher("/WEB-INF/views/fraud-result.jsp").forward(req, resp);
    }
}
*/




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






/*package com.frauddetection.controller;

import com.frauddetection.ml.DL4JFraudModel;
import com.frauddetection.ml.WekaFraudModel;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/fraud/check")
public class FraudCheckServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        double amount = Double.parseDouble(req.getParameter("amount"));
        int age = Integer.parseInt(req.getParameter("age"));
        String paymentType = req.getParameter("paymentType");

        double wekaScore = WekaFraudModel.predictFraud(amount, age, paymentType);
        double dl4jScore = DL4JFraudModel.predictFraud(amount, age, paymentType);
        double finalScore = (wekaScore + dl4jScore) / 2.0;

        req.setAttribute("amount", amount);
        req.setAttribute("paymentType", paymentType);
        req.setAttribute("wekaScore", wekaScore);
        req.setAttribute("dl4jScore", dl4jScore);
        req.setAttribute("finalScore", finalScore);

        req.getRequestDispatcher("/views/fraud-result.jsp").forward(req, resp);
    }
}
*/