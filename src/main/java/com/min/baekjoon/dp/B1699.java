package main.java.com.min.baekjoon.dp;

import java.util.Scanner;

public class B1699 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        long[] dp = new long[N + 1];

        dp[0] = 0;
        dp[1] = 1;

        for (int i = 0; i < N + 1; i++) {
            dp[i] = i;
        }

        for (int i = 2; i < N + 1; i++) {
            for (int j = 2; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        System.out.println(dp[N]);
    }
}
