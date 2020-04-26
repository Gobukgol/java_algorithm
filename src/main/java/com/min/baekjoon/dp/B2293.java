package main.java.com.min.baekjoon.dp;

import java.util.Scanner;

public class B2293 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] coin = new int[n];
        int[] dp = new int[k + 1];

        for (int i = 0; i < n; i++) {
            coin[i] = scanner.nextInt();
        }

        dp[0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < k + 1; j++) {
                if (j >= coin[i]) {
                    dp[j] += dp[j - coin[i]];
                }
            }
        }

        System.out.println(dp[k]);
    }
}
