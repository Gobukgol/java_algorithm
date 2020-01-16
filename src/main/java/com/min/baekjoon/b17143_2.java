package com.min.baekjoon;

import java.util.*;

public class b17143_2 {
    static String expression;
    static int expLength;
    static boolean[] bracket;
    static List<String> num;
    static List<String> opt;
    static List<String> tmp;
    static long max;

    public static void main(String[] args) {
        init();
        tmp.add(calc(num.get(0), opt.get(0), num.get(1)));
        bracket[0] = true;
        dfs(tmp, 1); // 첫번째 괄호 친거
        tmp.remove(tmp.size() - 1);
        bracket[0] = false;
        tmp.add(num.get(0));
        tmp.add(opt.get(0));
        dfs(tmp, 1); // 첫번째 괄호 안친거
        System.out.println(max);
    }

    public static void init() {
        Scanner scanner = new Scanner(System.in);
        expLength = Integer.parseInt(scanner.nextLine());
        expression = scanner.nextLine();

        tmp = new ArrayList<>();
        num = new ArrayList<>();
        opt = new ArrayList<>();

        for (int i = 0; i < expression.length(); i++) {
            if (i % 2 == 0) {
                num.add(String.valueOf(expression.charAt(i)));
            } else {
                opt.add(String.valueOf(expression.charAt(i)));
            }
        }
        bracket = new boolean[expLength / 2];
        Arrays.fill(bracket, false);
        Double m = (Math.pow(2, 31) * -1);
        max = m.longValue();
    }

    public static void dfs(List<String> exp, int curIdx) {
        if (curIdx >= bracket.length) { //계산
            List<String> calc = new ArrayList<>();
            for (int i = 0; i < exp.size(); i++) {
//                if(exp.get(i))
            }
//            if(max < calc) {
//                max = calc;
//            }
            return;
        }

        if (bracket[curIdx - 1]) {
            tmp.add(opt.get(curIdx));
            dfs(tmp, curIdx + 1);
            tmp.remove(tmp.size() - 1);
        } else {
            tmp.add(num.get(curIdx));
            tmp.add(opt.get(curIdx));
            dfs(tmp, curIdx + 1);
            tmp.remove(tmp.size() - 1);
            tmp.remove(tmp.size() - 1);
            bracket[curIdx] = true;
            tmp.add(calc(num.get(curIdx), opt.get(curIdx), num.get(curIdx + 1)));
            dfs(tmp, curIdx + 1);
            tmp.remove(tmp.size() - 1);
            bracket[curIdx] = false;
        }
    }

    public static String calc(String lOpr, String opt, String rOpr) {
        long result = 0;
        switch (opt) {
            case "+":
                result = Long.parseLong(lOpr) + Long.parseLong(rOpr);
                break;
            case "-":
                result = Long.parseLong(lOpr) - Long.parseLong(rOpr);
                break;
            case "*":
                result = Long.parseLong(lOpr) * Long.parseLong(rOpr);
                break;
        }

        return Long.toString(result);
    }
}
