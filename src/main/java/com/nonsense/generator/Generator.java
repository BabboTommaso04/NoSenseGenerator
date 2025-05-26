package com.nonsense.generator;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

public class Generator {
    private final String outputPath;

    // List of templates for sentence generation
    private final List<String[]> templates = List.of(
        new String[]{"The", ":NOUN:SINGULAR", ":VERB:SINGULAR", "the", ":NOUN:SINGULAR", "in", "the", ":ADJ:SINGULAR", "empty", "house."},
        new String[]{"At", "noon,", "the", ":ADJ:SINGULAR", ":NOUN:SINGULAR", ":VERB:SINGULAR", "and", "the", ":NOUN:PLURAL", ":VERB:PLURAL", "together."},
        new String[]{"Before", "sunset,", "a", ":NOUN:SINGULAR", ":VERB:SINGULAR", "the", ":ADJ:SINGULAR", ":NOUN:PLURAL", ":VERB:PLURAL", "nearby."},
        new String[]{"The", ":NOUN:SINGULAR", ":VERB:SINGULAR", "a", ":ADJ:SINGULAR", ":NOUN:SINGULAR", "as", ":NOUN:PLURAL", ":VERB:PLURAL", "."},
        new String[]{"While", "the", ":ADJ:SINGULAR", ":NOUN:SINGULAR", ":VERB:SINGULAR,", ":NOUN:PLURAL", ":VERB:PLURAL", "in", "the", "background."}
    );

    public Generator() {
        // Default output path for generated sentences
        this.outputPath = "src/main/resources/generated_sentences.txt";
    }

    public Generator(String outputPath) {
        // Constructor that allows setting a custom output path, for testing purposes
        this.outputPath = outputPath;
    }

    public String generate(int templateIndex, Dictionary dictionary){
        if(templateIndex == 0){ // It means the user wants a random template
            templateIndex = (int) (Math.random() * templates.size()) + 1; // +1 to shift the range to [1, size], because 0 means random
        }

        String[] chosenTemplate = templates.get(templateIndex - 1); // -1 because the list is 0-indexed
        StringBuilder generatedSentence = new StringBuilder();

        boolean inputUsed = false;

        for(String token : chosenTemplate){
            if(token.startsWith(":")){
                // Split the token to get the tag and number
                // Example token: ":NOUN:SINGULAR"
                String[] parts = token.split(":");
                String tag = parts[1];
                String number = parts[2];

                Word word;
                if(!inputUsed){ // If the program has not used the input yet
                    // Try to take a word from the temporary set first
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
        
        dictionary.clearTemporaryWords(); // Clear temporary words after generation

        try {
            // True to append to the file and not to overwrite
            FileWriter writer = new FileWriter(outputPath, true);
            writer.write(generatedSentence.toString().trim() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }

        return generatedSentence.toString().trim(); // Trim to remove the last space
    }
}