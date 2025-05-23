package com.nonsense.generator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SentenceAnalyzerTest {
    private SentenceAnalyzer analyzer;

    @BeforeEach
    public void setUp() throws IOException {
        analyzer = new SentenceAnalyzer();
    }

    @Test
    public void testSyntaxAnalyzer_withTree() {
        String sentence = "The quick brown fox jumps over the lazy dog.";
        Dictionary dictionary = new Dictionary();

        String result = analyzer.syntaxAnalyzer(sentence, true, dictionary);

        String expectedTree = """
        jumps (ROOT)
            ├── fox (NSUBJ)
            │   ├── The (DET)
            │   ├── quick (AMOD)
            │   └── brown (AMOD)
            ├── over (PREP)
            │   └── dog (POBJ)
            │       ├── the (DET)
            │       └── lazy (AMOD)
            └── . (P)
        """;

        assertNotNull(result);
        assertTrue(result.equals(expectedTree));
        assertTrue(
            result.contains("jumps (ROOT)") && 
            result.contains("fox (NSUBJ)") && 
            result.contains("The (DET)") &&
            result.contains("quick (AMOD)") &&
            result.contains("brown (AMOD)") &&
            result.contains("over (PREP)") &&
            result.contains("dog (POBJ)") &&
            result.contains("the (DET)") &&
            result.contains("lazy (AMOD)") &&
            result.contains(". (P)")
        );
        assertFalse(
            dictionary.takeWord("NOUN", "SINGULAR") == null &&
            dictionary.takeWord("NOUN", "PLURAL") == null && 
            dictionary.takeWord("VERB", "SINGULAR") == null && 
            dictionary.takeWord("VERB", "PLURAL") == null && 
            dictionary.takeWord("ADJ", "SINGULAR") == null, "Dictionary should be populated."
        );
    }

    @Test
    public void testSyntaxAnalyzer_withoutTree() {
        String sentence = "He is eating an apple.";
        Dictionary dictionary = new Dictionary();

        String result = analyzer.syntaxAnalyzer(sentence, false, dictionary);

        assertEquals("", result); // No tree returned
        assertFalse(
            dictionary.takeWord("NOUN", "SINGULAR") == null && 
            dictionary.takeWord("NOUN", "PLURAL") == null && 
            dictionary.takeWord("VERB", "SINGULAR") == null && 
            dictionary.takeWord("VERB", "PLURAL") == null && 
            dictionary.takeWord("ADJ", "SINGULAR") == null, "Dictionary should contain extracted words."
        );
    }

    @Test
    public void testEmptyInput() {
        String sentence = "";
        Dictionary dictionary = new Dictionary();

        String result = analyzer.syntaxAnalyzer(sentence, true, dictionary);

        assertEquals("", result);
        assertTrue(
            dictionary.takeWord("NOUN", "SINGULAR") == null && 
            dictionary.takeWord("NOUN", "PLURAL") == null && 
            dictionary.takeWord("VERB", "SINGULAR") == null && 
            dictionary.takeWord("VERB", "PLURAL") == null && 
            dictionary.takeWord("ADJ", "SINGULAR") == null
        );
    }

    @Test
    public void testMultipleSentences() {
        String sentence = "The cat sleeps. They play outside.";
        Dictionary dictionary = new Dictionary();

        String result = analyzer.syntaxAnalyzer(sentence, true, dictionary);

        String expectedTree = """
        sleeps (ROOT)
            ├── cat (NSUBJ)
            │   └── The (DET)
            └── . (P)
        play (ROOT)
            ├── They (NSUBJ)
            ├── outside (ADVMOD)
            └── . (P)
        """;

        assertNotNull(result);
        assertTrue(result.equals(expectedTree));
        assertTrue(
            result.contains("sleeps (ROOT)") && 
            result.contains("cat (NSUBJ)") && 
            result.contains("The (DET)") && 
            result.contains(". (P)")
        );
        assertTrue(
            result.contains("play (ROOT)") && 
            result.contains("They (NSUBJ)") && 
            result.contains("outside (ADVMOD)") && 
            result.contains(". (P)")
        );
        assertFalse(dictionary.takeWord("NOUN", "SINGULAR") == null && 
            dictionary.takeWord("NOUN", "PLURAL") == null && 
            dictionary.takeWord("VERB", "SINGULAR") == null && 
            dictionary.takeWord("VERB", "PLURAL") == null && 
            dictionary.takeWord("ADJ", "SINGULAR") == null
        );
    }
}