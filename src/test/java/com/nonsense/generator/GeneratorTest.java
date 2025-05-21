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
        dictionary.add(new Word("NOUN", "Dog", "SINGULAR", 1)); // questa finirà in temporaryWords
        dictionary.add(new Word("VERB", "runs", "SINGULAR", 2));
        dictionary.add(new Word("NOUN", "Cats", "PLURAL", 3));

        Generator generator = new Generator();
        String sentence = generator.generate(1, dictionary); // template 1 usa NOUN:SINGULAR e VERB:SINGULAR

        assertTrue(sentence.contains("Dog")); // la parola temporanea deve essere usata
        assertTrue(sentence.contains("runs"));
    }

    @Test
    void testGenerateAppendsToFile() throws Exception {
        Dictionary dictionary = new Dictionary();
        dictionary.add(new Word("NOUN", "Bird", "SINGULAR", 1));
        dictionary.add(new Word("VERB", "flies", "SINGULAR", 2));
        dictionary.add(new Word("NOUN", "Trees", "PLURAL", 3));

        Generator generator = new Generator();

        Path filePath = Path.of("src/main/resources/generated_sentences.txt");
        long before = Files.exists(filePath) ? Files.size(filePath) : 0;

        generator.generate(1, dictionary);

        long after = Files.size(filePath);
        assertTrue(after > before);
    }

    @Test
    void testGenerateReturnsNonEmptySentence() {
        Dictionary dictionary = new Dictionary();
        dictionary.add(new Word("NOUN", "Book", "SINGULAR", 1));
        dictionary.add(new Word("VERB", "opens", "SINGULAR", 2));
        dictionary.add(new Word("NOUN", "Shelves", "PLURAL", 3));

        Generator generator = new Generator();
        String result = generator.generate(1, dictionary);

        assertNotNull(result);
        assertFalse(result.isBlank());
    }
}