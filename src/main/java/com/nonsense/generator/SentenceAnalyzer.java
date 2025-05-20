package com.nonsense.generator;
import com.google.cloud.language.v1.*;
import com.google.cloud.language.v1.Document.Type;
import com.google.protobuf.ListValue;   
import java.io.IOException;
import java.util.*;

public class SentenceAnalyzer { 
    com.google.cloud.language.v1.LanguageServiceClient language;

    public SentenceAnalyzer() throws IOException {
        this.language = com.google.cloud.language.v1.LanguageServiceClient.create(); // creazione del ServiceClient nel try, fa l'autenticazione con le variabili di ambiente estratte dal json
    }

    private List<Token> APIHandler(String inputString) {
        com.google.cloud.language.v1.Document doc = com.google.cloud.language.v1.Document.newBuilder().setContent(inputString).setType(com.google.cloud.language.v1.Document.Type.PLAIN_TEXT).build(); // costruzione del documento da analizzare
        AnalyzeSyntaxRequest request = AnalyzeSyntaxRequest.newBuilder().setDocument(doc).setEncodingType(com.google.cloud.language.v1.EncodingType.UTF16).build(); // richiesta di analisi sintattica
            
        AnalyzeSyntaxResponse response = language.analyzeSyntax(request); // invio della richiesta
        return response.getTokensList(); // creazione lista dei token;
    }

    public String syntaxAnalyzer(String inputString, boolean showSyntaxTree, Dictionary dictionary) {
        List<Token> tokens =  this.APIHandler(inputString);


        // Costruisci una mappa head → [dipendenti]
        Map<Integer, List<Integer>> tree = new HashMap<>();
        int rootIndex = -1;

        for (int i = 0; i < tokens.size(); i++) {
            int head = tokens.get(i).getDependencyEdge().getHeadTokenIndex();
            
            dictionary.add(new Word(tokens.get(i).getPartOfSpeech().getTag().name(), tokens.get(i).getText().getContent().toLowerCase(), tokens.get(i).getPartOfSpeech().getNumber().name(), head)); // aggiunta al dizionario

            if (head == i) {
                rootIndex = i; // è la radice (ROOT)
            }
            tree.computeIfAbsent(head, k -> new ArrayList<>()).add(i);
        }

        if(showSyntaxTree) { // creazione dell'albero se richiesto dall'utente
            return buildTreeString(tokens, tree, rootIndex, "", true);
        }
        return "";
    }

    private String buildTreeString(List<Token> tokens, Map<Integer, List<Integer>> tree, int index, String prefix, boolean isLast) {
        Token token = tokens.get(index);
        StringBuilder sb = new StringBuilder();

        String connector = prefix.isEmpty() ? "" : (isLast ? "└── " : "├── ");
        sb.append(prefix).append(connector).append(token.getText().getContent()).append(" (").append(token.getDependencyEdge().getLabel()).append(")\n");

        List<Integer> children = tree.getOrDefault(index, Collections.emptyList()); 
        for (int i = 0; i < children.size(); i++) {
            int childIdx = children.get(i);
            if (childIdx != index) {
                boolean childIsLast = (i == children.size() - 1);
                String childPrefix = prefix + (isLast ? "    " : "│   ");
                sb.append(buildTreeString(tokens, tree, childIdx, childPrefix, childIsLast));
            }
        }
        return sb.toString();
    }
}