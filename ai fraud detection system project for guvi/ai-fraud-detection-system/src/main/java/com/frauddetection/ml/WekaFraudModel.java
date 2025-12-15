package com.frauddetection.ml;

import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;

public class WekaFraudModel {

    private static Classifier classifier;
    private static Instances header;

    static {
        try {
            // load classifier from resources (works with InputStream)
            classifier = (Classifier) SerializationHelper.read(
                    WekaFraudModel.class.getClassLoader().getResourceAsStream("ml/fraud-weka.model")
            );
            header = FeatureExtractor.buildWekaHeader();
        } catch (Exception e) {
            e.printStackTrace();
            classifier = null;
            header = FeatureExtractor.buildWekaHeader(); // fallback
        }
    }

    /**
     * Returns probability of fraud (0.0 - 1.0). If model unavailable, returns simple rule fallback.
     */
    public static double predictFraud(double amount, int age, String paymentType) {
        try {
            if (classifier == null) {
                // fallback simple rule
                return fallbackRule(amount, age, paymentType);
            }
            Instance inst = FeatureExtractor.createWekaInstance(amount, age, paymentType, header);
            double[] dist = classifier.distributionForInstance(inst);
            // assume index 1 is "fraud"
            if (dist.length > 1) return dist[1];
            return dist[0];
        } catch (Exception e) {
            e.printStackTrace();
            return fallbackRule(amount, age, paymentType);
        }
    }

    private static double fallbackRule(double amount, int age, String paymentType) {
        double score = 0.0;
        if (amount > 10000) score += 0.8;
        if ("international".equalsIgnoreCase(paymentType)) score += 0.5;
        if (age < 18 || age > 80) score += 0.2;
        return Math.min(score, 1.0);
    }
}