// Controller.java
package com.nonsense.generator;

import java.io.IOException;

public class Controller {
    private final Dictionary dictionary;
    private final Generator generator;
    private final SentenceAnalyzer sentenceAnalyzer;
    private final ToxicAnalyzer toxicAnalyzer;

    public Controller() throws IOException {
        this.dictionary = new Dictionary();
        this.generator = new Generator();
        this.sentenceAnalyzer = new SentenceAnalyzer();
        this.toxicAnalyzer = new ToxicAnalyzer();
        
        // Load the dictionary from the file
        dictionary.loadFromFile("src/main/resources/dictionary.json");
    }

    public String processInput(String inputText, int templateIndex, boolean showTree) throws Exception {
        // Input validation
        if(inputText == null || inputText.trim().isEmpty()) {
            throw new IllegalArgumentException("Il testo inserito non può essere vuoto");
        }
        
        // Syntax analysis
        String treeOutput = sentenceAnalyzer.syntaxAnalyzer(inputText, showTree, dictionary);
        
        // Phrase generation
        String generatedSentence = generator.generate(templateIndex, dictionary);
        this.dictionary.saveToFile("src/main/resources/dictionary.json"); // Saves the dictionary after sentence generation
        
        // Toxicity check
        float toxicityScore = toxicAnalyzer.getCategoryConfidence(inputText, 0);
        
        // Output preparation
        StringBuilder result = new StringBuilder();
    
        if(showTree) {
            result.append(treeOutput).append("\n\n");
        }
        result.append(generatedSentence)
              .append("\nPunteggio tossicità: ").append(String.format("%.2f", toxicityScore));
        
        return result.toString();
    }
}