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
        dictionary.add(new Word("NOUN", "Dog", "SINGULAR", 1));
        dictionary.add(new Word("VERB", "runs", "SINGULAR", 2));
        dictionary.add(new Word("NOUN", "Cat", "SINGULAR", 3));
        dictionary.add(new Word("ADJ", "quiet", "SINGULAR", 4));
        dictionary.add(new Word("VERB", "chase", "PLURAL", 5));

        Generator generator = new Generator("src/test/java/com/nonsense/generator/resources/test_generated_sentences.txt");
        String sentence = generator.generate(1, dictionary);

        assertTrue(sentence.contains("Cat") || sentence.contains("chase") || sentence.contains("Dog") || sentence.contains("quiet") || sentence.contains("runs")); 
    }

    @Test
    void testGenerateReturnsNonEmptySentence() {
        Dictionary dictionary = new Dictionary();
        dictionary.add(new Word("NOUN", "Book", "SINGULAR", 1));
        dictionary.add(new Word("VERB", "opens", "SINGULAR", 2));
        dictionary.add(new Word("NOUN", "Shelves", "PLURAL", 3));
        dictionary.add(new Word("ADJ", "old", "SINGULAR", 4));
        dictionary.add(new Word("VERB", "fall", "PLURAL", 5));

        Generator generator = new Generator("src/test/java/com/nonsense/generator/resources/test_generated_sentences.txt");
        String result = generator.generate(2, dictionary);

        assertNotNull(result);
        assertFalse(result.isBlank());
    }
}