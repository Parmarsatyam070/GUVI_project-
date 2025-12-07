package com.frauddetection.ml;

import com.frauddetection.model.Transaction;

public interface FeatureExtractorOperations {
    double[] extractFeatures(Transaction t);
}