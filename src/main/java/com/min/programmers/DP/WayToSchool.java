package main.java.com.min.programmers.DP;

import java.util.Arrays;

public class WayToSchool {

    static class Solution {
        public int solution(int m, int n, int[][] puddles) {
            int answer = 0;

            int[][] map = new int[m][n];
            for (int i = 0; i < m; i++) {
                Arrays.fill(map[i], 1);
            }

            for (int i = 0; i < puddles.length; i++) { // 웅덩이 초기화
                map[puddles[i][0] - 1][puddles[i][1] - 1] = 0;

                if (puddles[i][0] - 1 == 0) { // 집과 같은 행
                    for (int j = puddles[i][1] - 1; j < n; j++) {
                        map[0][j] = 0;
                    }
                }

                if (puddles[i][1] - 1 == 0) { // 집과 같은 열
                    for (int j = puddles[i][0] - 1; j < m; j++) {
                        map[j][0] = 0;
                    }
                }
            }

            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    if (map[i][j] == 0) {
                        continue;
                    }
                    map[i][j] = (map[i][j - 1] + map[i - 1][j]) % 1000000007;
                }
            }

            answer = map[m - 1][n - 1];

            return answer;
        }
    }
}
