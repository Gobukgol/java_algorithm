package main.java.com.min.programmers.DP;

import java.util.Arrays;

public class CardGame {

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.solution(new int[]{3, 2, 5}, new int[]{2, 4, 1}));
    }

    static class Solution {
        public int solution(int[] left, int[] right) {
            int[][] dp = new int[left.length + 1][right.length + 1];

            for (int i = left.length - 1; i >= 0; i--) {
                for (int j = right.length - 1; j >= 0; j--) {
                    if (left[i] > right[j]) dp[i][j] = dp[i][j + 1] + right[j]; //오른쪽 카드가 더 작다
                    else dp[i][j] = Math.max(dp[i + 1][j], dp[i + 1][j + 1]);
                }
            }
            return dp[0][0];

        }
    }
}
