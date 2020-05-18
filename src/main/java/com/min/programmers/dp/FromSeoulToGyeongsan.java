package main.java.com.min.programmers.dp;

public class FromSeoulToGyeongsan {

    public static void main(String[] args) {

    }

    public static int solution(int K, int[][] travel) {
        int answer = 0;

        //0은 도보, 1은 자전거
        int[][] dp = new int[travel.length][K + 1];
        int idx = travel.length;
        int walkT = travel[0][0];
        int walkM = travel[0][1];
        int bicyT = travel[0][2];
        int bicyM = travel[0][3];

        if (walkT == bicyT) {
            dp[0][walkT] = Math.max(walkM, bicyM);
        } else {
            dp[0][walkT] = walkM;
            dp[0][bicyT] = bicyM;
        }

        for (int i = 1; i < idx; i++) {
            for (int j = 0; j < K + 1; j++) {
                if (dp[i - 1][j] == 0) {
                    continue;
                }
                walkT = travel[i][0];
                walkM = travel[i][1];
                bicyT = travel[i][2];
                bicyM = travel[i][3];

                if (j + walkT <= K) {
                    dp[i][j + walkT] = Math.max(dp[i - 1][j] + walkM, dp[i][j + walkT]);
                }

                if (j + bicyT <= K) {
                    dp[i][j + bicyT] = Math.max(dp[i - 1][j] + bicyM, dp[i][j + bicyT]);
                }
            }
        }

        for (int i = 0; i < K + 1; i++) {
            answer = Math.max(dp[travel.length - 1][i], answer);
        }

        return answer;
    }
}
