package main.java.com.min.baekjoon.dp;

import java.util.Scanner;

public class B11055 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int[] A = new int[N];
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = scanner.nextInt();
        }

        dp[0] = A[0];

        for (int i = 1; i < N; i++) {
            dp[i] = A[i];
            for (int j = 0; j < i; j++) {
                if (A[i] > A[j]) {
                    if (dp[j] + A[i] > dp[i]) {
                        dp[i] = dp[j] + A[i];
                    }
                }
            }
        }

        int answer = dp[0];
        for (int i = 1; i < N; i++) {
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }
}
