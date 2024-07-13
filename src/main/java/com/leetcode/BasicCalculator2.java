package com.leetcode;

import java.util.LinkedList;

public class BasicCalculator2 {

    public static void main(String[] args) {
        // TODO

        System.out.println(new BasicCalculator2().calculate(" 3+5 / 2 "));
    }
    int convertToInt(char c) {

        // * = 4, / = 3, - = 2, + = 1

        if (c == '*') {
            return 4;
        } else if (c == '/') {
            return 3;
        } else if (c == '-') {
            return 2;
        } else if (c == '+') {
            return 1;
        } else {
            throw new IllegalArgumentException(c + "");
        }
    }

    int performOperation(int a, int b, int operator) {

        if (operator == 4) {
            return a * b;
        } else if (operator == 3) {
            return a / b;
        } else if (operator == 2) {
            // need to handle negatives
            return a - b;
        } else if (operator == 1) {
            return a + b;
        } else {
            throw new IllegalArgumentException(operator + "");
        }

    }


    public int calculate(String s) {

        LinkedList<Integer> operands = new LinkedList<>();
        LinkedList<Integer> operator = new LinkedList<>();
        StringBuilder integer = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if (c == ' ') {

                // ignore space
                continue;

            } else if (c == '+' || c == '-' || c == '/' || c == '*') {

                if (integer.length() > 0) {
                    operands.push(Integer.parseInt(integer.toString()));
                    integer.setLength(0);
                }

                int currentOperator = convertToInt(c);

                while (operands.size() >= 2) {
                    // at least two operands required to perform operation
                    // Requires operator.peek() has higher or equal precedence to perform operation

                    if (operator.size() == 0
                            || operands.size() == 1
                            || operator.peek() < currentOperator) {
                        break;
                    }

                    int a = operands.pop();
                    int b = operands.pop();
                    int r = performOperation(b, a, operator.pop());
                    operands.push(r);
                }

                operator.push(currentOperator);
            } else {

                integer.append(c);
            }
        }

        if (integer.length() > 0) {
            operands.push(Integer.parseInt(integer.toString()));
        }

        while (operands.size() > 1) {
            int a = operands.pop();
            int b = operands.pop();
            int r = performOperation(b, a, operator.pop());
            operands.push(r);
        }

        return operands.pop();
    }

}
