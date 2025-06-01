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
        dictionary.add("NOUN", "Dog", "SINGULAR");
        dictionary.add("NOUN", "Cats", "PLURAL");
        dictionary.add("VERB", "runs", "SINGULAR");
        dictionary.add("VERB", "fly", "PLURAL");
        dictionary.add("ADJ", "blue", "SINGULAR"); // Adjective number will be ignored

        assertEquals("dog", dictionary.takeWord("NOUN", "SINGULAR").getContent());
        assertEquals("cats", dictionary.takeWord("NOUN", "PLURAL").getContent());
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
        dictionary.add("VERB", "dance", "SINGULAR");

        Word temp = dictionary.takeFromTemporary("VERB", "SINGULAR");
        assertNotNull(temp);
        assertEquals("dance", temp.getContent());
    }

    @Test
    void testClearTemporaryWords() {
        Dictionary dictionary = new Dictionary();
        dictionary.add("NOUN", "banana", "SINGULAR");
        assertNotNull(dictionary.takeFromTemporary("NOUN", "SINGULAR"));
        dictionary.clearTemporaryWords();
        assertNull(dictionary.takeFromTemporary("NOUN", "SINGULAR"));
    }

    @Test
    void testNoDuplicatesInSets() {
        Dictionary dictionary = new Dictionary();
        dictionary.add("NOUN", "Table", "SINGULAR");
        dictionary.add("NOUN", "Table", "SINGULAR");

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
        original.add("NOUN", "Laptop", "SINGULAR");
        original.add("VERB", "compute", "PLURAL");
        original.add("ADJ", "smart", "SINGULAR");

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