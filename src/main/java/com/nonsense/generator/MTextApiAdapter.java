package com.nonsense.generator;
import com.google.cloud.language.v1.*;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.*;

import java.util.Objects;
import java.io.IOException;

public class MTextApiAdapter implements ToxicAnalyzer {

    private String currentSentence = null;
    private float[] conf = new float[ClassificationCategoryName.values().length];

    @Override
    public float getCategoryConfidence(String sentence, ClassificationCategoryName category)  throws IOException {

        Objects.requireNonNull(sentence, "sentence must be not null");
        Objects.requireNonNull(category, "category must be not null");

        int index = category.ordinal();

        //mini-cache
        if(currentSentence == null) currentSentence = sentence;
        else if(sentence.equals(currentSentence)) return conf[index];

        ModerateTextResponse res;

        try (LanguageServiceClient lsc = LanguageServiceClient.create()) {
            Document doc = Document.newBuilder().setContent(sentence).setType(Type.PLAIN_TEXT).build();
            res = lsc.moderateText(doc);
        }

        int i = 0;
        for (ClassificationCategory entity : res.getModerationCategoriesList()) {
            conf[i] = entity.getConfidence();
            i++;
        }

        return conf[index];
    }
}
