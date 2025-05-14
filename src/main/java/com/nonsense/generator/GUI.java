package src.main.java.com.nonsense.generator;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class GUI extends JFrame {
    private JTextField inputField;
    private JCheckBox syntaxTreeCheckbox;
    private JComboBox<String> templateDropdown;
    private JButton generateButton;
    
    public GUI() {
        setTitle("Nonsense Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 200);
        setLayout(new BorderLayout(10, 10));

        // Panel principale
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Input frase
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.add(new JLabel("Inserisci frase:"));
        inputField = new JTextField(30);
        inputPanel.add(inputField);
        mainPanel.add(inputPanel);

         // Checkbox e Dropdown - MODIFICATO
         JPanel optionsPanel = new JPanel();
         optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
         
         // Checkbox syntax tree
         JPanel checkboxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
         syntaxTreeCheckbox = new JCheckBox("Visualizza albero sintattico");
         checkboxPanel.add(syntaxTreeCheckbox);
         optionsPanel.add(checkboxPanel);
 
         // Dropdown menu
         JPanel dropdownPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
         String[] templates = {"Template 1", "Template 2", "Template 3"};
         templateDropdown = new JComboBox<>(templates);
         dropdownPanel.add(new JLabel("Seleziona template:"));
         dropdownPanel.add(templateDropdown);
         optionsPanel.add(dropdownPanel);
         
         mainPanel.add(optionsPanel);

        // Pulsante Generate
        JPanel buttonPanel = new JPanel();
        generateButton = new JButton("Generate");
        generateButton.addActionListener(new GenerateButtonListener());
        buttonPanel.add(generateButton);
        mainPanel.add(buttonPanel);

        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
        
        
    }

    private class GenerateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String inputText = inputField.getText(); // Get the input text
            if (inputText.isEmpty()) {
                JOptionPane.showMessageDialog(GUI.this, "Please enter a phrase.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean visualizeTree = syntaxTreeCheckbox.isSelected(); // Get the checkbox state
            String selectedTemplate = (String) templateDropdown.getSelectedItem(); // Get the selected template
            System.out.println("Input: " + inputText); //DA TOGLIERE
            System.out.println("Visualize Tree: " + visualizeTree); //DA TOGLIERE
            System.out.println("Selected Template: " + selectedTemplate); //DA TOGLIERE                        
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI());
    }
}