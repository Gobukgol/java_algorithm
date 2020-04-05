package main.java.com.min.baekjoon.dp;

import java.util.Scanner;

public class B2193 {
    static int N;
    static long[] dp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        if (N == 1) {
            System.out.println(1);
            return;
        }

        dp = new long[N];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i < N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        System.out.println(dp[N - 1]);
        return;
    }
}
