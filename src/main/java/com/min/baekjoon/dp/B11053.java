package main.java.com.min.baekjoon.dp;

import java.util.Scanner;

public class B11053 {
    static int N;
    static int[] A;
    static int[] dp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        A = new int[N];
        dp = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = scanner.nextInt();
        }
        dp[0] = 1;
        for (int i = 1; i < N; i++) {
            int min = 0;
            for (int j = 0; j < i; j++) {
                if (A[i] > A[j]) {
                    min = Math.max(min, dp[j]);
                }
            }
            dp[i] = min + 1;
        }
        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }
}
