package com.calculator.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Stack;

public class MainWindowController {

    @FXML
    private Button clear;
    @FXML
    private Label currState;
    @FXML
    private Button plus;
    @FXML
    private Button minus;
    @FXML
    private Button mul;
    @FXML
    private Button div;
    @FXML
    private Label ans;
    @FXML
    private Button equal;
    @FXML
    private Button zero;
    @FXML
    private Button three;
    @FXML
    private Button one;
    @FXML
    private Button four;
    @FXML
    private Button five;
    @FXML
    private Button two;
    @FXML
    private Button six;
    @FXML
    private Button seven;
    @FXML
    private Button eight;
    @FXML
    private Button nine;
    @FXML
    private Button dot;

    public void plusButtonClicked(ActionEvent actionEvent) {
        currState.setText(currState.getText() + " + ");
    }

    public void minusButtonClicked(ActionEvent actionEvent) {
        currState.setText(currState.getText() + " - ");
    }

    public void mulButtonClicked(ActionEvent actionEvent) {
        currState.setText(currState.getText() + " * ");
    }

    public void divButtonClicked(ActionEvent actionEvent) {
        currState.setText(currState.getText() + " / ");
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private int precedence(char c) {
        if (c == '+' || c == '-') return 1;
        if (c == '*' || c == '/') return 2;
        return 0;
    }

    private double evaluateExpression(String s) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c) || c == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < s.length() && (Character.isDigit(s.charAt(i)) || s.charAt(i) == '.')) {
                    sb.append(s.charAt(i++));
                }
                i--;
                numbers.push(Double.parseDouble(sb.toString()));
            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (operators.peek() != '(') {
                    numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
                }
                operators.pop();
            } else if (isOperator(c)) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(c)) {
                    numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
                }
                operators.push(c);
            }
        }

        while (!operators.isEmpty()) {
            numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
        }

        return numbers.pop();
    }

    private double applyOperation(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b != 0) return a / b;
                else throw new ArithmeticException("Division by zero");
        }
        return 0;
    }

    private void calculate() {
        try {
            double result = evaluateExpression(currState.getText());
            ans.setText(Double.toString(result));
        } catch (ArithmeticException e) {
            ans.setText("Division by zero");
        } catch (Exception e) {
            ans.setText("Wrong Format");
            e.printStackTrace();
        }
    }

    public void equalButtonClicked(ActionEvent actionEvent) {
        calculate();
    }

    public void oneClicked(ActionEvent actionEvent) {
        currState.setText(currState.getText() + "1");
    }

    public void threeClicked(ActionEvent actionEvent) {
        currState.setText(currState.getText() + "3");
    }

    public void twoClicked(ActionEvent actionEvent) {
        currState.setText(currState.getText() + "2");
    }

    public void fourClicked(ActionEvent actionEvent) {
        currState.setText(currState.getText() + "4");
    }

    public void fiveClicked(ActionEvent actionEvent) {
        currState.setText(currState.getText() + "5");
    }

    public void sixClicked(ActionEvent actionEvent) {
        currState.setText(currState.getText() + "6");
    }

    public void sevenClicked(ActionEvent actionEvent) {
        currState.setText(currState.getText() + "7");
    }

    public void eightClicked(ActionEvent actionEvent) {
        currState.setText(currState.getText() + "8");
    }

    public void nineClicked(ActionEvent actionEvent) {
        currState.setText(currState.getText() + "9");
    }

    public void zeroClicked(ActionEvent actionEvent) {
        currState.setText(currState.getText() + "0");
    }

    public void dotClicked(ActionEvent actionEvent) {
        currState.setText(currState.getText() + ".");
    }

    public void clearClicked(ActionEvent actionEvent) {
        ans.setText("");
        currState.setText("");
    }
}
