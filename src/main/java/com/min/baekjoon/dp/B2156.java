package main.java.com.min.baekjoon.dp;

import java.util.Scanner;

public class B2156 {
    static int n;
    static int[] wines;
    static int[] dp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        n = scanner.nextInt();
        wines = new int[n];
        dp = new int[n];

        for (int i = 0; i < n; i++) {
            wines[i] = scanner.nextInt();
        }

        if (n == 1) {
            System.out.println(wines[0]);
            return;
        }
        if (n == 2) {
            System.out.println(wines[0] + wines[1]);
            return;
        }
        dp[0] = wines[0];
        dp[1] = wines[0] + wines[1];
        dp[2] = Math.max(dp[0] + wines[2], dp[1]);
        dp[2] = Math.max(dp[2], wines[1] + wines[2]);


        for (int i = 3; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + wines[i], dp[i - 1]);
            dp[i] = Math.max(dp[i], dp[i - 3] + wines[i - 1] + wines[i]);
        }

        System.out.println(dp[n - 1]);
    }
}
