package main.java.com.min.baekjoon.dp;

import java.util.Scanner;

public class B2163 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] dp = new int[n * m + 1];

        dp[1] = 0;

        for (int i = 2; i <= n * m; i++) {
            dp[i] = dp[i / 2] + dp[i - (i / 2)] + 1;
        }

        System.out.println(dp[n * m]);
    }
}
