package com.nonsense.generator;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class GeneratorTest {

    @Test
    void testGenerateWithInputWordUsed() {
        Dictionary dictionary = new Dictionary();
        dictionary.add("NOUN", "Dog", "SINGULAR");
        dictionary.add("VERB", "runs", "SINGULAR");
        dictionary.add("NOUN", "Cat", "SINGULAR");
        dictionary.add("ADJ", "quiet", "SINGULAR");
        dictionary.add("VERB", "chase", "PLURAL");

        // Using the generator constuctor with a file path, only for testing purposes
        Generator generator = new Generator("src/test/java/com/nonsense/generator/resources/test_generated_sentences.txt");
        String sentence = generator.generate(1, dictionary);

        assertTrue(sentence.contains("Cat") || sentence.contains("chase") || sentence.contains("Dog") || sentence.contains("quiet") || sentence.contains("runs")); 
    }

    @Test
    void testGenerateReturnsNonEmptySentence() {
        Dictionary dictionary = new Dictionary();
        dictionary.add("NOUN", "Book", "SINGULAR");
        dictionary.add("VERB", "opens", "SINGULAR");
        dictionary.add("NOUN", "Shelves", "PLURAL");
        dictionary.add("ADJ", "old", "SINGULAR");
        dictionary.add("VERB", "fall", "PLURAL");

        Generator generator = new Generator("src/test/java/com/nonsense/generator/resources/test_generated_sentences.txt");
        String result = generator.generate(2, dictionary);

        assertNotNull(result);
        assertFalse(result.isBlank());
    }
}