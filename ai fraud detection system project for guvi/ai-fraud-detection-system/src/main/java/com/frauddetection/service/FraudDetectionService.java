package com.frauddetection.service;

import com.frauddetection.dao.FraudDAO;
import com.frauddetection.model.FraudAlert;
import com.frauddetection.model.Transaction;
import com.frauddetection.ml.DL4JFraudModel;
import com.frauddetection.ml.WekaFraudModel;

public class FraudDetectionService {

    private final FraudDAO fraudDAO = new FraudDAO();

    /**
     * Run both models, average score, store alert if fraud threshold exceeded, and return final score
     */
    public double analyzeAndStore(Transaction tx) {
        double wekaP = WekaFraudModel.predictFraud(tx.getAmount(), tx.getAge(), tx.getPaymentType());
        double dl4jP = DL4JFraudModel.predictFraud(tx.getAmount(), tx.getAge(), tx.getPaymentType());

        double finalScore = (wekaP + dl4jP) / 2.0;
        tx.setFraudScore(finalScore);

        boolean isFraud = finalScore >= 0.7; // threshold
        FraudAlert alert = new FraudAlert(tx.getId(), isFraud, finalScore);
        fraudDAO.save(alert);

        return finalScore;
    }
}