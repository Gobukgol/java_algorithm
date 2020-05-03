package main.java.com.min.baekjoon.dp;

import java.util.Arrays;
import java.util.Scanner;

public class B11057 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[][] dp = new int[N][10];

        Arrays.fill(dp[0], 1);

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 10; j++) {
                int sum = 0;
                for (int k = j; k >= 0; k--) {
                    sum = (sum + dp[i - 1][k]) % 10007;
                }
                dp[i][j] = sum;
            }
        }

        int answer = 0;
        for (int i = 0; i < 10; i++) {
            answer = (answer + dp[N - 1][i]) % 10007;
        }

        System.out.println(answer);
    }
}
