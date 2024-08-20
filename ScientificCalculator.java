import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ScientificCalculator extends JFrame implements ActionListener {
    private JTextField display;
    private StringBuilder currentInput;
    private boolean startNewNumber;

    // Define a functional interface for unary operations
    @FunctionalInterface
    interface UnaryFunction {
        double apply(double x);
    }

    public ScientificCalculator() {
        currentInput = new StringBuilder();
        startNewNumber = true;

        // Create the display area
        display = new JTextField("0");
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);

        // Create buttons
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "√", "^", "sin", "cos",
            "tan", "C", "Del"
        };

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4));

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        // Set up the frame
        this.setLayout(new BorderLayout());
        this.add(display, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.CENTER);

        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "C":
                clear();
                break;
            case "Del":
                deleteLastDigit();
                break;
            case "=":
                calculateResult();
                break;
            case "√":
                applyUnaryFunction(Math::sqrt);
                break;
            case "^":
                currentInput.append("^");
                startNewNumber = false;
                break;
            case "sin":
                applyUnaryFunction(Math::sin);
                break;
            case "cos":
                applyUnaryFunction(Math::cos);
                break;
            case "tan":
                applyUnaryFunction(Math::tan);
                break;
            default:
                handleInput(command);
                break;
        }
    }

    private void clear() {
        currentInput.setLength(0);
        display.setText("0");
        startNewNumber = true;
    }

    private void deleteLastDigit() {
        if (currentInput.length() > 0) {
            currentInput.setLength(currentInput.length() - 1);
            display.setText(currentInput.toString());
        }
        if (currentInput.length() == 0) {
            display.setText("0");
        }
    }

    private void handleInput(String input) {
        if (startNewNumber && !"+-*/^".contains(input)) {
            currentInput.setLength(0);
            startNewNumber = false;
        }
        currentInput.append(input);
        display.setText(currentInput.toString());
        if (input.equals("=")) {
            calculateResult();
        }
    }

    private void calculateResult() {
        try {
            double result = evaluateExpression(currentInput.toString());
            display.setText(String.valueOf(result));
            currentInput.setLength(0);
            currentInput.append(result);
            startNewNumber = true;
        } catch (Exception ex) {
            display.setText("Error");
            currentInput.setLength(0);
        }
    }

    private double evaluateExpression(String expression) throws Exception {
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();
        char[] tokens = expression.toCharArray();

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == ' ') continue;

            if (tokens[i] >= '0' && tokens[i] <= '9') {
                StringBuilder sb = new StringBuilder();
                while (i < tokens.length && (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.')) {
                    sb.append(tokens[i++]);
                }
                values.push(Double.parseDouble(sb.toString()));
                i--;
            } else if (tokens[i] == '(') {
                operators.push(tokens[i]);
            } else if (tokens[i] == ')') {
                while (operators.peek() != '(') {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                }
                operators.pop();
            } else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                while (!operators.empty() && precedence(tokens[i]) <= precedence(operators.peek())) {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(tokens[i]);
            } else if (tokens[i] == '^') {
                while (!operators.empty() && precedence(tokens[i]) <= precedence(operators.peek())) {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(tokens[i]);
            }
        }

        while (!operators.empty()) {
            values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    private int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    private double applyOperation(char operator, double b, double a) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) throw new ArithmeticException("Cannot divide by zero");
                return a / b;
            case '^':
                return Math.pow(a, b);
        }
        throw new UnsupportedOperationException("Invalid operator");
    }

    private void applyUnaryFunction(UnaryFunction function) {
        try {
            double number = Double.parseDouble(currentInput.toString());
            double result = function.apply(number);
            display.setText(String.valueOf(result));
            currentInput.setLength(0);
            currentInput.append(result);
            startNewNumber = true;
        } catch (Exception ex) {
            display.setText("Error");
        }
    }

    public static void main(String[] args) {
        new ScientificCalculator();
    }
}
