package com.nonsense.generator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.IOException;


/**
 * Unit test for ToxicAnalyzer. Value against which the methods are tested were gathered calling the REST api.
 */
public class ToxicAnalyzerTest {

    private static ToxicAnalyzer ta;
    /*
     * This array was (manuallly) filled reading the response of:
     * curl -X POST                                                 \
     * -H "Authorization: Bearer $(gcloud auth print-access-token)" \
     * -H "x-goog-user-project: $PROJECT_ID"                        \
     * -H "Content-Type: application/json; charset=utf-8"           \
     * -d @request.json                                             \
     * "https://translation.googleapis.com/language/translate/v2"
     * (one calling for the empty string and one for the "shut up!" string)
     */
    private static final float[][] results = new float[][]{
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0.72981346f, 0.5891868f, 0.23585929f, 0.016035471f, 0.0090325875f, 0.030120483f, 0.12804878f, 0f, 0.020568071f, 0.013533108f, 0.012507818f, 0.00955414f, 0.010416667f, 0.019292604f, 0.014897579f, 0.011976048f}
    };

    @BeforeEach
    void init() {
        ta = new MTextApiAdapter();
    }

    @Test
    void exceptionTesting() {
        assertThrows(NullPointerException.class, () -> ta.getCategoryConfidence(null, ClassificationCategoryName.INSULT));
        assertThrows(NullPointerException.class, () -> ta.getCategoryConfidence("hi", null));
        assertThrows(NullPointerException.class, () -> ta.getCategoryConfidence(null, null));
    }

    private void forEachNameTest(float results[], String msg) throws IOException {
        ta.getCategoryConfidence(msg, ClassificationCategoryName.INSULT);
        for(int i = 0; i < ClassificationCategoryName.values().length; i++){
            String error = ClassificationCategoryName.values()[i] + "[" + msg + "]";
            assertEquals(ta.getCategoryConfidence(msg, ClassificationCategoryName.values()[i]), results[i], error);
        }
    }

    @Test
    void EmptyStringTest()throws IOException{
        forEachNameTest(results[0], "");
    }

    @Test
    void InsultTest()throws IOException{
        forEachNameTest(results[1], "shut up!");
    }

}
