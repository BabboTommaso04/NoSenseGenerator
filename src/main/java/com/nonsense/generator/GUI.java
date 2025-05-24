package com.nonsense.generator;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

/**
 * GUI class for the Nonsense Generator application.
 * Manages user interaction, input/output UI, and displays generated results.
 */
public class GUI extends JFrame {
    private JTextField inputField; // Text field where the user inputs a sentence
    private JCheckBox syntaxTreeCheckbox; // Checkbox to enable/disable syntax tree visualization
    private JComboBox<String> templateDropdown; // Dropdown to select a template for sentence generation
    private JButton generateButton; // Button to trigger the generation of a nonsensical sentence
    private Controller controller; // Controller to handle the logic of the application

    public GUI() { // Constructor
        // Set up the main frame
        setTitle("Non Sense Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 200);
        setLayout(new BorderLayout(10, 10));
        // Center the window on the screen
        try {
            controller = new Controller();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, 
                "Errore di inizializzazione: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        JPanel mainPanel = createMainPanel();
        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }
    
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(createInputPanel());
        mainPanel.add(createOptionsPanel());
        mainPanel.add(createButtonPanel());
        
        return mainPanel;
    }
    // Creates the input panel with a text field for user input
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel("Write a sentence:"));
        inputField = new JTextField(30);
        panel.add(inputField);
        return panel;
    }
    // Creates the options panel with a checkbox and a dropdown for template selection
    private JPanel createOptionsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JPanel checkboxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        syntaxTreeCheckbox = new JCheckBox("Visulize Syntax Tree");
        checkboxPanel.add(syntaxTreeCheckbox);
        
        JPanel dropdownPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String[] templates = {"Random", "The NOUN VERB the NOUN in the ADJ empty house" , "At noon, the ADJ NOUN VERB and the NOUN VERB together" , "Before sunset, a NOUN VERB the ADJ NOUN VERB nearby", "The NOUN VERB a ADJ NOUN as NOUN VERB", "While the ADJ NOUN VERB, NOUN VERB in the background"};
        templateDropdown = new JComboBox<>(templates);
        dropdownPanel.add(new JLabel("Template:"));
        dropdownPanel.add(templateDropdown);
        
        panel.add(checkboxPanel);
        panel.add(dropdownPanel);
        return panel;
    }
    // Creates the button panel with a button to generate the nonsensical sentence
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        generateButton = new JButton("Generate");
        generateButton.addActionListener(new GenerateButtonListener());
        panel.add(generateButton);
        return panel;
    }
    // Listener for the generate button
    // When clicked, it processes the input and generates a nonsensical sentence
    private class GenerateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String inputText = inputField.getText().trim();
            if (inputText.isEmpty()) {
                showError("Error", "Insert a correct sentence");
                return;
            }
            
            try {
                boolean visualizeTree = syntaxTreeCheckbox.isSelected();
                int templateIndex = templateDropdown.getSelectedIndex();
                
                String result = controller.processInput(inputText, templateIndex, visualizeTree);
                showResultWindow(result, visualizeTree);
                
            } catch (Exception ex) {
                showError("Errore di elaborazione", ex.getMessage());
            }
        }
    }
    // Displays the result in a new window
    // The result includes the generated sentence, syntax tree (if requested), and toxicity score
    private void showResultWindow(String result, boolean showTree) {
        JDialog resultDialog = new JDialog(this, "Non Sense Generator", true);
        resultDialog.setLayout(new BorderLayout());
        resultDialog.setSize(500, 450);
        resultDialog.setLocationRelativeTo(this);
    
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Split the result into parts
        String treeContent = "";
        String generatedText = "";
        String toxicity = "";
    
        if(showTree && result.contains("\n\n")) {
            String[] parts = result.split("\n\n", 2);
            treeContent = parts[0];
            String[] mainParts = parts[1].split("Punteggio tossicità: ");
            generatedText = mainParts[0].replace("FRASE GENERATA: ", "").trim();
            toxicity = mainParts.length > 1 ? mainParts[1] : "";
        } else {
            String[] mainParts = result.split("Punteggio tossicità: ");
            generatedText = mainParts[0].replace("FRASE GENERATA: ", "").trim();
            toxicity = mainParts.length > 1 ? mainParts[1] : "";
        }
    
        // Generated sentence
        JTextArea generatedArea = new JTextArea(generatedText);
        generatedArea.setEditable(false);
        contentPanel.add(createSectionPanel("Generated Sentence", generatedArea));
    
        // Syntax tree (if requested)
        if(showTree && !treeContent.isEmpty()) {
            JTextArea treeArea = new JTextArea(treeContent);
            treeArea.setEditable(false);
            contentPanel.add(createSectionPanel("Syntax Tree", treeArea));
        }
    
        // Toxicity score
        JLabel toxicityLabel = new JLabel("Toxicity: " + toxicity);
        toxicityLabel.setForeground(Color.RED);
        contentPanel.add(createSectionPanel("Analysis", toxicityLabel));
    
        resultDialog.add(new JScrollPane(contentPanel), BorderLayout.CENTER);
        resultDialog.setVisible(true);
    }
        // Creates a panel with a title and a component
        private JPanel createSectionPanel(String title, Component component) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));
        
        if(component instanceof JTextArea) {
            JScrollPane scrollPane = new JScrollPane(component);
            panel.add(scrollPane, BorderLayout.CENTER);
        } else {
            panel.add(component, BorderLayout.CENTER);
        }
        
        return panel;
    }
    // Displays an error message in a dialog
    private void showError(String title, String message) {
        JOptionPane.showMessageDialog(this, 
            message, 
            title, 
            JOptionPane.ERROR_MESSAGE);
    }

}