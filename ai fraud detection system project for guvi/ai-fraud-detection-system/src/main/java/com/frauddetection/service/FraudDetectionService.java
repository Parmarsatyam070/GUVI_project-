/*package com.frauddetection.service;

import com.frauddetection.model.Transaction;
import com.frauddetection.util.IConstants;

public class FraudDetectionService implements FraudDetectionServiceOperations {
    @Override
    public boolean isFraudulent(Transaction transaction) {
        return transaction.getAmount() > IConstants.FRAUD_SCORE_THRESHOLD * IConstants.MAX_TRANSACTION_AMOUNT;
    }
}
*/



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



/*package com.frauddetection.service;

import com.frauddetection.model.Transaction;

public class FraudDetectionService {

    // Dummy ML logic (replace with DL4J or WEKA later)
    public boolean isFraudulent(Transaction tx) {
        if (tx.getAmount() > 10000) return true;
        if (tx.getLocation().equalsIgnoreCase("international")) return true;
        return false;
    }
}
*/