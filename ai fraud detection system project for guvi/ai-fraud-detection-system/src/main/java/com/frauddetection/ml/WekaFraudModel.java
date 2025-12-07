/*package com.frauddetection.ml;

import weka.classifiers.Classifier;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class WekaFraudModel {

    private Classifier classifier;
    private Instances datasetFormat;

    public WekaFraudModel() {
        try {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream("src/main/resources/ml/fraud-weka.model")
            );

            classifier = (Classifier) ois.readObject();
            datasetFormat = (Instances) ois.readObject();
            datasetFormat.setClassIndex(datasetFormat.numAttributes() - 1);

            ois.close();

        } catch (Exception e) {
            throw new RuntimeException("Failed to load Weka model", e);
        }
    }

    public double predict(double[] features) {
        try {
            Instance instance = new DenseInstance(1.0, features);
            instance.setDataset(datasetFormat);
            return classifier.classifyInstance(instance);
        } catch (Exception e) {
            throw new RuntimeException("Weka prediction failed", e);
        }
    }
}*/







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








/*package com.frauddetection.ml;

import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

public class WekaFraudModel {

    private static Classifier classifier;
    private static Instances modelHeader;

    static {
        try {
            classifier = (Classifier) weka.core.SerializationHelper.read(
                    WekaFraudModel.class.getClassLoader()
                            .getResourceAsStream("ml/fraud-weka.model")
            );

            ConverterUtils.DataSource source = new ConverterUtils.DataSource(
                    WekaFraudModel.class.getClassLoader()
                            .getResourceAsStream("ml/fraud-schema.arff")
            );

            modelHeader = source.getStructure();
            modelHeader.setClassIndex(modelHeader.numAttributes() - 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static double predictFraud(double amount, int age, String paymentType) {
        try {
            Instance instance = FeatureExtractor.createWekaInstance(
                    amount, age, paymentType, modelHeader
            );

            double[] distribution = classifier.distributionForInstance(instance);

            return distribution[1]; // 1 = FRAUD
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }
}*/

