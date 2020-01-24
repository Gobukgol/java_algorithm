package com.min.programmers.kakao.blind_2020;

import java.util.Stack;

//정확성 100점
public class BracketTrans {
    public static void main(String[] args) {
        System.out.println(solution("()))((()"));
    }

    public static String solution(String p) {
        return getCorrectBracket(p);
    }

    public static String getCorrectBracket(String w) {
        if (checkCorrectBracket(w)) {
            return w;
        }
        String[] brackets = separateBracket(w);
        String afterV = getCorrectBracket(brackets[1]);
        if (checkCorrectBracket(brackets[0])) {
            return brackets[0].concat(afterV);
        }

        int currentIdx = 0;
        char[] tmp = new char[w.length()];

        tmp[currentIdx++] = '(';
        for (int i = 0; i < afterV.length(); i++) {
            tmp[currentIdx++] = afterV.charAt(i);
        }
        tmp[currentIdx++] = ')';

        for (int i = 1; i < brackets[0].length() - 1; i++) {
            char tmpChar = brackets[0].charAt(i);
            if (tmpChar == '(') {
                tmp[currentIdx++] = ')';
            } else {
                tmp[currentIdx++] = '(';
            }
        }

        return String.valueOf(tmp);
    }

    public static String[] separateBracket(String w) {
        String[] brackets = new String[2];
        int leftCnt = 0;
        int rightCnt = 0;

        for (int i = 0; i < w.length(); i++) {
            if (w.charAt(i) == '(') {
                leftCnt++;
            } else {
                rightCnt++;
            }

            if (leftCnt == rightCnt) {
                brackets[0] = w.substring(0, i + 1);
                brackets[1] = w.substring(i + 1);
                break;
            }
        }

        return brackets;
    }

    public static boolean checkCorrectBracket(String w) {
        Stack<Character> stack = new Stack();

        for (int i = 0; i < w.length(); i++) {
            if (w.charAt(i) == '(') {
                stack.push(w.charAt(i));
            } else {
                if (!stack.isEmpty()) {
                    if (stack.peek() == '(') {
                        stack.pop();
                    }
                }
            }
        }

        return stack.isEmpty();
    }
}
