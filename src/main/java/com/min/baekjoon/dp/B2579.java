package main.java.com.min.baekjoon.dp;

import java.io.IOException;
import java.util.Scanner;

public class B2579 {
    static int N;
    static int[] stair;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        stair = new int[N];
        for (int i = 0; i < N; i++) {
            stair[i] = scanner.nextInt();
        }

        dp = new int[N];
        dp[0] = stair[0];
        if (N > 1) {
            dp[1] = Math.max(stair[0] + stair[1], stair[1]);
        }
        if (N > 2) {
            dp[2] = Math.max(stair[1] + stair[2], stair[0] + stair[2]);
        }

        for (int i = 3; i < N; i++) {
            dp[i] = Math.max(dp[i - 3] + stair[i - 1] + stair[i], dp[i - 2] + stair[i]);
        }

        System.out.println(dp[N - 1]);
    }
}
