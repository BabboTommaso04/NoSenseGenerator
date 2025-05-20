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
        
        // Caricamento dizionario predefinito
        dictionary.loadFromFile("src/main/resources/dictionary.json");
    }

    public String processInput(String inputText, int templateIndex, boolean showTree) throws Exception {
        // Validazione input
        if(inputText == null || inputText.trim().isEmpty()) {
            throw new IllegalArgumentException("Il testo inserito non può essere vuoto");
        }
        
        // Analisi sintattica
        String treeOutput = sentenceAnalyzer.syntaxAnalyzer(inputText, showTree, dictionary);
        
        // Generazione frase
        String generatedSentence = generator.generate(templateIndex, dictionary);
        
        // Controllo tossicità
        float toxicityScore = toxicAnalyzer.getCategoryConfidence(inputText, 0);
        
        // Preparazione output
        StringBuilder result = new StringBuilder();
    
        if(showTree) {
            result.append(treeOutput).append("\n\n");
        }
        result.append(generatedSentence)
              .append("\nPunteggio tossicità: ").append(String.format("%.2f", toxicityScore));
        
        return result.toString();
    }
}