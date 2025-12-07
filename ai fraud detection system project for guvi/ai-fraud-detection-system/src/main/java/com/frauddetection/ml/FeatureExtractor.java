/*package com.frauddetection.ml;

import com.frauddetection.model.Transaction;

/**
 * FeatureExtractor extracts input features for ML models.
 */
/*public class FeatureExtractor implements FeatureExtractorOperations {

    @Override
    public double[] extractFeatures(Transaction txn) {
        try {
            return new double[]{
                    txn.getAmount(),
                    txn.getMerchant() != null ? txn.getMerchant().length() : 0
            };
        } catch (Exception e) {
            return new double[]{0.0, 0.0};
        }
    }
}
*/



package com.frauddetection.ml;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;

import java.util.ArrayList;
import java.util.List;

public class FeatureExtractor {

    public static Instances buildWekaHeader() {
        ArrayList<Attribute> atts = new ArrayList<>();

        atts.add(new Attribute("amount"));
        atts.add(new Attribute("age"));
        atts.add(new Attribute("paymentType"));

        List<String> classVals = new ArrayList<>();
        classVals.add("safe");
        classVals.add("fraud");

        atts.add(new Attribute("class", classVals));

        Instances header = new Instances("fraud_header", atts, 0);
        header.setClassIndex(header.numAttributes() - 1);

        return header;
    }

    public static Instance createWekaInstance(double amount, int age, String paymentType, Instances header) {

        double[] vals = new double[header.numAttributes()];
        vals[0] = amount;
        vals[1] = age;
        vals[2] = encodePaymentType(paymentType);
        vals[3] = Utils.missingValue();   // FIXED

        Instance inst = new DenseInstance(1.0, vals);
        inst.setDataset(header);

        return inst;
    }

    public static INDArray createDL4JVector(double amount, int age, String paymentType) {
        return Nd4j.create(new double[]{
                amount,
                age,
                encodePaymentType(paymentType)
        }).reshape(1, 3);
    }

    private static double encodePaymentType(String p) {
        if (p == null) return 0;

        return switch (p.toLowerCase()) {
            case "credit" -> 1;
            case "debit" -> 2;
            case "upi" -> 3;
            case "wallet" -> 4;
            case "international" -> 5;
            default -> 0;
        };
    }
}





/*package com.frauddetection.ml;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class FeatureExtractor {

    public static Instance createWekaInstance(double amount, int age, String paymentType,
                                              Instances header) {

        Instance instance = new DenseInstance(header.numAttributes());
        instance.setDataset(header);

        instance.setValue(0, amount);
        instance.setValue(1, age);
        instance.setValue(2, encodePaymentType(paymentType));

        return instance;
    }

    public static INDArray createDL4JVector(double amount, int age, String paymentType) {

        return Nd4j.create(new double[]{
                amount,
                age,
                encodePaymentType(paymentType)
        });
    }

    private static int encodePaymentType(String type) {
        return switch (type.toLowerCase()) {
            case "credit" -> 1;
            case "debit" -> 2;
            case "upi" -> 3;
            case "wallet" -> 4;
            default -> 0;
        };
    }
}
*/