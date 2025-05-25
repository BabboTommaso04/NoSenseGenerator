package com.nonsense.generator;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class DictionaryTest {

    @Test
    void testAddAndTakeEachCategory() {
        Dictionary dictionary = new Dictionary();
        dictionary.add(new Word("NOUN", "Dog", "SINGULAR", 1));
        dictionary.add(new Word("NOUN", "Cats", "PLURAL", 2));
        dictionary.add(new Word("VERB", "runs", "SINGULAR", 3));
        dictionary.add(new Word("VERB", "fly", "PLURAL", 4));
        dictionary.add(new Word("ADJ", "blue", "SINGULAR", 5)); // Adjective number will be ignored

        assertEquals("Dog", dictionary.takeWord("NOUN", "SINGULAR").getContent());
        assertEquals("Cats", dictionary.takeWord("NOUN", "PLURAL").getContent());
        assertEquals("runs", dictionary.takeWord("VERB", "SINGULAR").getContent());
        assertEquals("fly", dictionary.takeWord("VERB", "PLURAL").getContent());
        assertEquals("blue", dictionary.takeWord("ADJ", "PLURAL").getContent());
    }

    @Test
    void testTakeWordEmpty() {
        Dictionary dictionary = new Dictionary();
        assertNull(dictionary.takeWord("NOUN", "SINGULAR"));
        assertNull(dictionary.takeWord("VERB", "PLURAL"));
        assertNull(dictionary.takeWord("ADJ", "SINGULAR"));
    }

    @Test
    void testTakeFromTemporary() {
        Dictionary dictionary = new Dictionary();
        Word w = new Word("VERB", "dance", "SINGULAR", 1);
        dictionary.add(w);

        Word temp = dictionary.takeFromTemporary("VERB", "SINGULAR");
        assertNotNull(temp);
        assertEquals("dance", temp.getContent());
    }

    @Test
    void testClearTemporaryWords() {
        Dictionary dictionary = new Dictionary();
        dictionary.add(new Word("NOUN", "banana", "SINGULAR", 1));
        assertNotNull(dictionary.takeFromTemporary("NOUN", "SINGULAR"));
        dictionary.clearTemporaryWords();
        assertNull(dictionary.takeFromTemporary("NOUN", "SINGULAR"));
    }

    @Test
    void testNoDuplicatesInSets() {
        Dictionary dictionary = new Dictionary();
        Word w1 = new Word("NOUN", "Table", "SINGULAR", 1);
        Word w2 = new Word("NOUN", "Table", "SINGULAR", 1);
        dictionary.add(w1);
        dictionary.add(w2);

        Set<String> uniqueContents = new HashSet<>(); // HashSet to ensure uniqueness of the elements
        for (int i = 0; i < 10; i++) {
            Word w = dictionary.takeWord("NOUN", "SINGULAR");
            if (w != null) uniqueContents.add(w.getContent());
        }
        assertEquals(1, uniqueContents.size());
    }

    @Test
    void testSaveAndLoadDictionary() {
        Dictionary original = new Dictionary();
        original.add(new Word("NOUN", "Laptop", "SINGULAR", 1));
        original.add(new Word("VERB", "compute", "PLURAL", 2));
        original.add(new Word("ADJ", "smart", "SINGULAR", 3));

        String path = "src/test/java/com/nonsense/generator/test_dictionary.json";
        original.saveToFile(path);  

        Dictionary loaded = new Dictionary();
        loaded.loadFromFile(path);

        assertNotNull(loaded.takeWord("NOUN", "SINGULAR"));
        assertNotNull(loaded.takeWord("VERB", "PLURAL"));
        assertNotNull(loaded.takeWord("ADJ", "SINGULAR"));

        new File(path).delete(); // Clean up the test file after the test
    }
}