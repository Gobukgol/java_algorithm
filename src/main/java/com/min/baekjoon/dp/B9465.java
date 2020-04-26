package main.java.com.min.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            int[][] stickers = new int[2][n];
            int[][] dp = new int[2][n + 1];
            for (int j = 0; j < 2; j++) {
                String[] tmp = br.readLine().split(" ");
                for (int k = 0; k < n; k++) {
                    stickers[j][k] = Integer.parseInt(tmp[k]);
                }
            }

            dp[0][0] = 0;
            dp[1][0] = 0;
            dp[0][1] = stickers[0][0];
            dp[1][1] = stickers[1][0];
            for (int k = 2; k < n + 1; k++) {
                dp[0][k] = Math.max(dp[1][k - 1], dp[1][k - 2]) + stickers[0][k - 1];
                dp[1][k] = Math.max(dp[0][k - 1], dp[0][k - 2]) + stickers[1][k - 1];
            }

            System.out.println(Math.max(dp[0][n], dp[1][n]));
        }
    }
}
