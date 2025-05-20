package com.nonsense.generator;
import java.util.*;
import java.io.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class Dictionary {
    private Set<Word> singularNouns = new HashSet<>();
    private Set<Word> pluralNouns = new HashSet<>();
    private Set<Word> singularVerbs = new HashSet<>();
    private Set<Word> pluralVerbs = new HashSet<>();
    private Set<Word> adjectives = new HashSet<>();
    
    private Set<Word> temporaryWords = new HashSet<>();
    private Random random = new Random();

    // Adds a word to the dictionary based on its tag and number
    public void add(Word word) {
        String tag = word.getTag();
        String number = word.getNumber();

        switch (tag) {
            case "NOUN":
                if ("SINGULAR".equals(number)) {
                    singularNouns.add(word);
                } else if ("PLURAL".equals(number)) {
                    pluralNouns.add(word);
                }
                break;
            case "VERB":
                if ("SINGULAR".equals(number)) {
                    singularVerbs.add(word);
                } else if ("PLURAL".equals(number)) {
                    pluralVerbs.add(word);
                }
                break;
            case "ADJ":
            case "ADJECTIVE":
                adjectives.add(word);
                break;
            default:
                break;
        }
        
        temporaryWords.add(word); // Temporary set of words from the current input sentence
    }

    // Extracts a random word from the dictionary based on requested tag and number
    public Word takeWord(String tag, String number) {
        Set<Word> candidates = new HashSet<>();
        switch (tag) {
            case "NOUN":
                candidates = "SINGULAR".equals(number) ? singularNouns : pluralNouns;
                break;
            case "VERB":
                candidates = "SINGULAR".equals(number) ? singularVerbs : pluralVerbs;
                break;
            // Handles both ADJ and ADJECTIVE tags depending on what the API calls them
            case "ADJ":
            case "ADJECTIVE":
                candidates = adjectives;
                break;
            default:
                break;
        }

        // If there's no set to choose from, return null
        if (candidates.isEmpty()) return null;

        int index = random.nextInt(candidates.size());
        int i = 0;
        // Iterate through the set to find the word at the random index, since sets can't be accessed by index
        for (Word word : candidates) {
            if (i == index) {
                return word;
            }
            i++;
        }
        return null;  // Needed for compiler
    }

    // Clears the temporary words set
    public void clearTemporaryWords() {
        temporaryWords.clear();
    }

    public Word takeFromTemporary(String tag, String number) {
        // Ignores the number for adjectives
        List<Word> filtered = temporaryWords.stream()
            .filter(w -> w.getTag().equals(tag) &&
                (tag.equals("ADJ") || tag.equals("ADJECTIVE") || w.getNumber().equals(number)))
            .toList();

        if (filtered.isEmpty()) return null;

        int index = new Random().nextInt(filtered.size());
        return filtered.get(index); // Takes a random word from the filtered list
    }

    // Saves the dictionary to a JSON file
    public void saveToFile(String filePath) {
        Gson gson = new Gson(); //Create a Gson instance
        // Keys are the categories of the dictionary, values are the sets of words
        Map<String, Set<Word>> data = new HashMap<>();
        data.put("singularNouns", singularNouns);
        data.put("pluralNouns", pluralNouns);
        data.put("singularVerbs", singularVerbs);
        data.put("pluralVerbs", pluralVerbs);
        data.put("adjectives", adjectives);

        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Loads the dictionary from a JSON file
    public void loadFromFile(String filePath) {
        Gson gson = new Gson();
        // Type represents a generic type, in this case a map with string keys and set values
        // TypeToken is a Gson class that retains the type information at runtime
        Type type = new TypeToken<Map<String, Set<Word>>>() {}.getType();

        try (FileReader reader = new FileReader(filePath)) {
            // Gson reads the JSON file and converts it to a Map
            Map<String, Set<Word>> data = gson.fromJson(reader, type);
            if (data != null) {
                // If it finds the key "singularNouns" in the JSON file, it loads the corresponding set of words
                // If the key is not found, it initializes the set to an empty HashSet
                singularNouns = data.getOrDefault("singularNouns", new HashSet<>());
                pluralNouns = data.getOrDefault("pluralNouns", new HashSet<>());
                singularVerbs = data.getOrDefault("singularVerbs", new HashSet<>());
                pluralVerbs = data.getOrDefault("pluralVerbs", new HashSet<>());
                adjectives = data.getOrDefault("adjectives", new HashSet<>());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Dictionary file not found, starting with empty dictionary.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}