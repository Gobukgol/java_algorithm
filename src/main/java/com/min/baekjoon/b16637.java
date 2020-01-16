package com.min.baekjoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class b16637 {
    static int length;
    static String expression;
    static long result;
    static Long[][] dp;
    static List<String> opts;

    public static void main(String[] args) {
        init();

        for (int i = 2; i < dp.length; i++) {
            dp(i);
        }

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                if (dp != null) {
                    System.out.print(dp[i][j] + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    public static void init() {
        Scanner scanner = new Scanner(System.in);
        length = Integer.parseInt(scanner.nextLine());
        dp = new Long[length / 2 + 1][length / 2 + 1];
        expression = scanner.nextLine();
        opts = new ArrayList<>();
        for (int i = 1; i < length; i += 2) {
            opts.add(expression.substring(i, i + 1));
        }

        for (int i = 0; i < length / 2 + 1; i++) {
            dp[i][i] = Long.parseLong(expression.substring(i * 2, i * 2 + 1));
            if (i < length / 2) {
                long opr1 = Long.parseLong(expression.substring(i * 2, i * 2 + 1));
                long opr2 = Long.parseLong(expression.substring(i * 2 + 2, i * 2 + 3));
                String opt = opts.get(i);
                dp[i][i + 1] = calc(opr1, opr2, opt);
            }
        }
    }

    public static void dp(int level) {
        for (int i = level; i < dp.length; i++) {
            dp[i - level][i] = Math.max(calc(dp[i - level][i - level], dp[i - level + 1][i], opts.get(i - level)),
                    calc(dp[i - level][i - 1], dp[i][i], opts.get(i - 1)));
        }
    }

    public static long calc(long opr1, long opr2, String opt) {
        long result = 0;

        switch (opt) {
            case "+":
                result = opr1 + opr2;
                break;
            case "-":
                result = opr1 - opr2;
                break;
            case "*":
                result = opr1 * opr2;
                break;
        }

        return result;
    }

}
