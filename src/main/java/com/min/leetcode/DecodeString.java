package com.min.leetcode;

import java.util.Stack;

public class DecodeString {

    public static void main(String[] args) {
        System.out.println(decodeString("3[a]2[bc]"));
    }

    public static String decodeString(String s) {
        Stack<String> strings = new Stack<>();
        StringBuilder numberBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ']') {
                StringBuilder builder = new StringBuilder();
                while (true) {
                    if (strings.peek().equals("[")) {
                        strings.pop();
                        break;
                    }
                    builder.insert(0, strings.pop());
                }
                int number = Integer.parseInt(strings.pop());

                StringBuilder calcStr = new StringBuilder();
                for (int j = 0; j < number; j++) {
                    calcStr.append(builder.toString());
                }

                strings.push(calcStr.toString());
            } else if (s.charAt(i) >= 48 && s.charAt(i) <= 57) {
                numberBuilder.append(s.charAt(i));
            } else {
                if (s.charAt(i) == '[') {
                    strings.add(numberBuilder.toString());
                    numberBuilder = new StringBuilder();
                }

                strings.add(String.valueOf(s.charAt(i)));
            }
        }

        StringBuilder answer = new StringBuilder();

        while (!strings.empty()) {
            answer.insert(0, strings.pop());
        }

        return answer.toString();
    }
}
