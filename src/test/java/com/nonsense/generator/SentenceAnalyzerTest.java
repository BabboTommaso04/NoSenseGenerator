/*package com.nonsense.generator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SentenceAnalyzerTest {
    private SentenceAnalyzer analyzer;

    @BeforeAll
    static void setUpCredentials() {
        System.setProperty("GOOGLE_APPLICATION_CREDENTIALS", "/home/teo/nonsensegenerator-459922-b633dd6c0b8d.json");
    }

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
            tree.contains("jumps (ROOT)") && 
            tree.contains("fox (NSUBJ)") && 
            tree.contains("The (DET)") &&
            tree.contains("quick (AMOD)") &&
            tree.contains("brown (AMOD)") &&
            tree.contains("over (PREP)") &&
            tree.contains("dog (POBJ)") &&
            tree.contains("the (DET)") &&
            tree.contains("lazy (AMOD)") &&
            tree.contains(". (P)")
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
        ├── outside (ADV)
        └── . (P)
        """;

        assertNotNull(result);
        assertTrue(result.equals(expectedTree));
        assertTrue(
            tree.contains("sleeps (ROOT)") && 
            tree.contains("cat (NSUBJ)") && 
            tree.contains("The (DET)") && 
            tree.contains(". (P)")
        );
        assertTrue(
            tree.contains("play (ROOT)") && 
            tree.contains("They (NSUBJ)") && 
            tree.contains("outside (ADV)") && 
            tree.contains(". (P)")
        );
        assertFalse(dictionary.takeWord("NOUN", "SINGULAR") == null && 
            dictionary.takeWord("NOUN", "PLURAL") == null && 
            dictionary.takeWord("VERB", "SINGULAR") == null && 
            dictionary.takeWord("VERB", "PLURAL") == null && 
            dictionary.takeWord("ADJ", "SINGULAR") == null
        );
    }
}*/