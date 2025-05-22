package com.nonsense.generator;

import java.io.IOException;
public interface ToxicAnalyzer{

    /**
     * Returns a float whose value is the confidence that the {@code sentence} belongs to {@code category}.
     *
     * @param sentence, the sentence which is to be moderated
     * @param category, the classification category
     * @return the confidence
     * @throws throws IOException if can't communicate with the API server
     */
    public float getCategoryConfidence(String sentence, ClassificationCategoryName category)  throws IOException;
}
