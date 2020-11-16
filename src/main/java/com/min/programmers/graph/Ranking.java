package com.min.programmers.graph;

public class Ranking {
    public int solution(int n, int[][] results) {
        int answer = 0;

        boolean[][] ranks = new boolean[n + 1][n + 1];

        for (int[] result : results) {
            ranks[result[0]][result[1]] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    if (ranks[i][k] && ranks[k][j]) {
                        ranks[i][j] = true;
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            for (int j = 1; j <= n; j++) {
                if (ranks[i][j] || ranks[j][i]) {
                    cnt++;
                }
            }

            if (cnt == n - 1) {
                answer++;
            }
        }

        return answer;
    }
}
