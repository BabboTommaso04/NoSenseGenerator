package com.nonsense.generator;
import com.google.cloud.language.v1.*;
import com.google.cloud.language.v1.Document.Type;
import com.google.protobuf.ListValue;   
import java.io.IOException;
import java.util.*;

public class SentenceAnalyzer { 
    private com.google.cloud.language.v1.LanguageServiceClient language;

    public SentenceAnalyzer() throws IOException {
        this.language = com.google.cloud.language.v1.LanguageServiceClient.create(); // creation of the ServiceClient, doesa the autentication with the eniroment variables from the json
    }

    private List<Token> APIHandler(String inputString) {
        com.google.cloud.language.v1.Document doc = com.google.cloud.language.v1.Document.newBuilder().setContent(inputString).setType(com.google.cloud.language.v1.Document.Type.PLAIN_TEXT).build(); // building the document to analyze
        AnalyzeSyntaxRequest request = AnalyzeSyntaxRequest.newBuilder().setDocument(doc).setEncodingType(com.google.cloud.language.v1.EncodingType.UTF16).build(); // SyntaxAnalyze request
            
        AnalyzeSyntaxResponse response = language.analyzeSyntax(request); // send request
        return response.getTokensList(); // token list from the API response
    }

    public String syntaxAnalyzer(String inputString, boolean showSyntaxTree, Dictionary dictionary) {
        List<Token> tokens =  this.APIHandler(inputString);


        // Map headIndex → [pointingTokensList]
        Map<Integer, List<Integer>> tree = new HashMap<>();
        Vector<Integer> rootIndex = new Vector<>(); // vector of rootIndexes in case there are more than one phrase separated by a period
        int j = 0;
        rootIndex.add(-1);

        for (int i = 0; i < tokens.size(); i++) {
            int head = tokens.get(i).getDependencyEdge().getHeadTokenIndex();
            // add() is only for: nouns adjectives and verbs in the third pearson(both singular and plural)
            if((tokens.get(i).getPartOfSpeech().getTag().name().equals("VERB") && tokens.get(i).getPartOfSpeech().getPerson().name().equals("THIRD")) || tokens.get(i).getPartOfSpeech().getTag().name().equals("NOUN") || tokens.get(i).getPartOfSpeech().getTag().name().equals("ADJ")) { 
                dictionary.add(tokens.get(i).getPartOfSpeech().getTag().name(), tokens.get(i).getText().getContent().toLowerCase(), tokens.get(i).getPartOfSpeech().getNumber().name()); // add to the dictionary
            }

            if (head == i) {
                rootIndex.set(j, i); // rootIndex
                j++;
                rootIndex.add(-1);
            }
            tree.computeIfAbsent(head, k -> new ArrayList<>()).add(i);
        }

        String trees = "";
        if(showSyntaxTree) { // check on showSytaxTree
            for(int i = 0; i < j; i++) { //bulid one for each one of the rootIndexes found
                trees += buildTreeString(tokens, tree, rootIndex.get(i), "", true);
            } 
        }
        return trees;
    }

    private String buildTreeString(List<Token> tokens, Map<Integer, List<Integer>> tree, int index, String prefix, boolean isLast) { // writing of the syntax tree
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