package main.java.com.min.baekjoon.dp;

import java.util.Scanner;

public class B11727 {
    static int n;
    static int dp[];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        dp = new int[n];

        dp[0] = 1;

        if (n != 1) {
            dp[1] = 3;
        }

        for (int i = 2; i < n; i++) {
            dp[i] = (dp[i - 2] * 2 + dp[i - 1]) % 10007;
        }

        System.out.println(dp[n - 1]);
    }
}
