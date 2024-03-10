import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AestheticCalculator extends JFrame implements ActionListener {
    private JTextField inputField;
    private double num1, num2, result;
    private char operator;

    public AestheticCalculator() {
        // Use the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Frame setup
        setTitle("Aesthetic Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center the frame on the screen
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Input field
        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 20));
        inputField.setEditable(false);
        inputField.setBackground(new Color(230, 230, 255)); // Lavender background
        inputField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(200, 200, 255), 2), // Lighter lavender border
                new EmptyBorder(5, 10, 5, 10)
        ));
        add(inputField, BorderLayout.NORTH);

        // Buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(5, 4, 5, 5));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C", "CE"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setBackground(new Color(200, 200, 255)); // Lavender background
            button.setForeground(Color.DARK_GRAY); // Dark gray text
            button.setBorder(BorderFactory.createCompoundBorder(
                    new BevelBorder(BevelBorder.RAISED, new Color(180, 180, 230), new Color(160, 160, 210)),
                    new EmptyBorder(10, 15, 10, 15)
            ));
            buttonsPanel.add(button);
        }

        add(buttonsPanel, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AestheticCalculator());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "=":
                calculate();
                break;
            case "/":
            case "*":
            case "-":
            case "+":
                setOperator(command.charAt(0));
                break;
            case "C":
                clearInput();
                break;
            case "CE":
                clearAll();
                break;
            default:
                appendToInputField(command);
        }
    }

    private void calculate() {
        if (operator != 0) {
            num2 = Double.parseDouble(inputField.getText());
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        JOptionPane.showMessageDialog(this, "Cannot divide by zero.");
                        clearInputField();
                        return;
                    }
                    break;
            }
            inputField.setText(String.valueOf(result));
            operator = 0;
        }
    }

    private void setOperator(char op) {
        if (operator == 0) {
            num1 = Double.parseDouble(inputField.getText());
            operator = op;
            clearInputField();
        }
    }

    private void appendToInputField(String str) {
        inputField.setText(inputField.getText() + str);
    }

    private void clearInputField() {
        inputField.setText("");
    }

    private void clearInput() {
        clearInputField();
    }

    private void clearAll() {
        clearInput();
        num1 = 0;
        num2 = 0;
        result = 0;
        operator = 0;
    }
}
