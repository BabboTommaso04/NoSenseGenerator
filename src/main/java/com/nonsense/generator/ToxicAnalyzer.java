package com.nonsense.generator;
import com.google.cloud.language.v1.*;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.*;

import java.io.IOException;

public class ToxicAnalyzer {

    String currentSentence = null;
    float[] conf = new float[16];

    float getCategoryConfidence(String sentence, int index)  throws IOException {
        System.out.println(sentence + " index " + index);

        ModerateTextResponse res;

        try (LanguageServiceClient lsc = LanguageServiceClient.create()) {
            Document doc = Document.newBuilder().setContent(sentence).setType(Type.PLAIN_TEXT).build();
            res = lsc.moderateText(doc);
        }
        //for (ClassificationCategory entity : res.getModerationCategoriesList()) {
        //    System.out.printf("Entity: %s", entity.getName());
        //    System.out.println("confidence: " + entity.getConfidence());
        // }
        return res.getModerationCategories(index).getConfidence();

    }

    //public static void main(String[] args) throws IOException {
    //    System.out.println("Hello World!");
    //
    //    ToxicAnalyzer ta = new ToxicAnalyzer();
    //    System.out.println("toxicity: " + ta.getCategoryConfidence("cacca e merda! tette e culi! morte e inferno!", 0));
    //
    // }
}
