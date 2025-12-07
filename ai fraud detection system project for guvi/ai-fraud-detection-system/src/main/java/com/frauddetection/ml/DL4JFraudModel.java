/*package com.frauddetection.ml;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.io.File;

public class DL4JFraudModel {

    private MultiLayerNetwork model;

    public DL4JFraudModel() {
        try {
            File f = new File("src/main/resources/ml/fraud-model.bin");
            model = MultiLayerNetwork.load(f, true);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load DL4J model", e);
        }
    }

    public double predict(double[] features) {
        INDArray input = Nd4j.create(features);
        INDArray output = model.output(input);

        return output.getDouble(0);   // probability score
    }
}
*/






package com.frauddetection.ml;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

public class DL4JFraudModel {

    private static MultiLayerNetwork model;

    static {
        try {
            // Resource inside jar cannot be loaded directly as File -> copy to temp
            InputStream is = DL4JFraudModel.class.getClassLoader().getResourceAsStream("ml/fraud-model.bin");
            if (is != null) {
                File tmp = File.createTempFile("dl4j-fraud-", ".bin");
                tmp.deleteOnExit();
                Files.copy(is, tmp.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                model = ModelSerializer.restoreMultiLayerNetwork(tmp);
            } else {
                model = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            model = null;
        }
    }

    public static double predictFraud(double amount, int age, String paymentType) {
        try {
            if (model == null) {
                // fallback simple heuristic similar to Weka fallback
                return fallbackRule(amount, age, paymentType);
            }
            INDArray input = FeatureExtractor.createDL4JVector(amount, age, paymentType);
            // DL4J expects 2D row vectors: reshape if necessary
            if (input.rank() == 1) input = input.reshape(1, input.length());
            INDArray output = model.output(input);
            // assume single output neuron for probability or vector [safeScore, fraudScore]
            if (output.columns() == 1) {
                double v = output.getDouble(0);
                // ensure normalized to 0..1
                return Math.max(0.0, Math.min(1.0, v));
            } else {
                // assume second column is fraud probability
                return output.getDouble(0, Math.min(1, output.columns()-1));
            }
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

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;

public class DL4JFraudModel {

    private static MultiLayerNetwork model;

    static {
        try {
            model = MultiLayerNetwork.load(
                    DL4JFraudModel.class.getClassLoader()
                            .getResourceAsStream("ml/fraud-model.bin"),
                    true
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static double predictFraud(double amount, int age, String paymentType) {
        try {
            INDArray input = FeatureExtractor.createDL4JVector(amount, age, paymentType);

            INDArray output = model.output(input);

            return output.getDouble(1);  // fraud probability
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }
}
*/