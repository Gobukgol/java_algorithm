package com.min.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BasicCalculator2 {
    public static void main(String[] args) {
        System.out.println(calculate("14/3*2"));
    }

    public static int calculate(String s) {
        List<String> postfixFormula = toPostfix(toStrFormula(s));

        Stack<Integer> stack = new Stack<>();

        for (String op : postfixFormula) {
            int opr2, opr1;
            switch (op) {
                case "*":
                    opr2 = stack.pop();
                    opr1 = stack.pop();
                    stack.add(opr1 * opr2);
                    break;
                case "/":
                    opr2 = stack.pop();
                    opr1 = stack.pop();
                    stack.add(opr1 / opr2);
                    break;
                case "+":
                    opr2 = stack.pop();
                    opr1 = stack.pop();
                    stack.add(opr1 + opr2);
                    break;
                case "-":
                    opr2 = stack.pop();
                    opr1 = stack.pop();
                    stack.add(opr1 - opr2);
                    break;
                default:
                    stack.add(Integer.parseInt(op));
                    break;
            }
        }

        return stack.pop();
    }

    public static List<String> toStrFormula(String source) {
        source = source.replaceAll(" ", "");

        List<String> strs = new ArrayList<>();
        StringBuilder numberBuilder = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            char cur = source.charAt(i);
            if (cur == '+' || cur == '-' || cur == '*' || cur == '/') {
                strs.add(numberBuilder.toString());
                numberBuilder = new StringBuilder();
                strs.add(String.valueOf(cur));
            } else {
                numberBuilder.append(cur);
            }
        }
        strs.add(numberBuilder.toString());

        return strs;
    }

    public static List<String> toPostfix(List<String> formula) {
        List<String> postfix = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        for (String op : formula) {
            if (op.equals("*") || op.equals("/")) {
                while (!stack.isEmpty()) {
                    String top = stack.peek();
                    if (top.equals("*") || top.equals("/")) {
                        postfix.add(stack.pop());
                    } else {
                        break;
                    }
                }
                stack.add(op);
            } else if (op.equals("+") || op.equals("-")) {
                while (!stack.isEmpty()) {
                    postfix.add(stack.pop());
                }
                stack.add(op);
            } else {
                postfix.add(op);
            }
        }

        while (!stack.isEmpty()) {
            postfix.add(stack.pop());
        }

        return postfix;
    }
}
