package main.java.com.min.baekjoon.dp;

import java.util.Scanner;

public class B9461 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        long[] dp = new long[100];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 2;
        dp[4] = 2;
        for (int i = 5; i < 100; i++) {
            dp[i] = dp[i - 1] + dp[i - 5];
        }
        for (int i = 0; i < T; i++) {
            System.out.println(dp[scanner.nextInt() - 1]);
        }
    }
}
