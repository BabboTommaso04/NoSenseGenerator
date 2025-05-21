package com.nonsense.generator;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

public class Generator {
    private final List<String[]> templates = List.of(
        new String[]{"The", ":NOUN:SINGULAR", ":VERB:SINGULAR", "in", "the", "park."},
        new String[]{":NOUN:PLURAL", ":VERB:PLURAL", "quickly."},
        new String[]{"The", ":NOUN:SINGULAR", "watches", "the", ":NOUN:PLURAL"},
        new String[]{":NOUN:SINGULAR", "and", ":NOUN:SINGULAR", ":VERB:PLURAL", "together."},
        new String[]{"A", ":NOUN:SINGULAR", ":VERB:SINGULAR", "above", "the", ":NOUN:PLURAL"}
    );

    public String generate(int templateIndex, Dictionary dictionary){
        if(templateIndex == 0){ // It means the user wants a random template
            templateIndex = (int) (Math.random() * templates.size()) + 1; // +1 to shift the range to [1, size]
        }

        String[] chosenTemplate = templates.get(templateIndex - 1); // -1 because the list is 0-indexed
        StringBuilder generatedSentence = new StringBuilder();

        boolean inputUsed = false;

        for(String token : chosenTemplate){
            if(token.startsWith(":")){
                String[] parts = token.split(":");
                String tag = parts[1];
                String number = parts[2];


                Word word;
                if(!inputUsed){ // Try to take a word from the temporary set first
                    word = dictionary.takeFromTemporary(tag, number);
                    if(word != null){
                        inputUsed = true;
                    } else {
                        word = dictionary.takeWord(tag, number);
                    }
                } else {
                    word = dictionary.takeWord(tag, number);
                }

                if(word != null){
                    generatedSentence.append(word.getContent()).append(" ");
                } else {
                    generatedSentence.append("[MISSING]").append(" "); // Should not happen if the dictionary is well populated
                }

            } else {
                generatedSentence.append(token).append(" ");
            }
        }
        dictionary.saveToFile("src/main/resources/dictionary.json"); // Save the dictionary to a file after generation
        dictionary.clearTemporaryWords(); // Clear temporary words after generation

        try {
            // True to append to the file and not to overwrite
            FileWriter writer = new FileWriter("src/main/resources/generated_sentences.txt", true);
            writer.write(generatedSentence.toString().trim() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }

        return generatedSentence.toString().trim(); // Trim to remove the last space
    }
}