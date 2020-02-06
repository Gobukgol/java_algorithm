package com.min.baekjoon.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14890 {
    static int N, L;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        for (int i = 0; i < N; i++) {
            if (isCorrectRoad(i, true)) answer++;
            if (isCorrectRoad(i, false)) answer++;
        }

        System.out.println(answer);
    }

    public static boolean isCorrectRoad(int start, boolean isRow) {
        int[] road = new int[N];
        for (int i = 0; i < N; i++) {
            road[i] = isRow ? map[start][i] : map[i][start];
        }

        int before = road[0];
        int flatCnt = 1;
        for (int i = 1; i < N; i++) {
            switch (before - road[i]) {
                case -1: // 경사로 위
                    if (flatCnt < L) {
                        return false;
                    }
                    flatCnt = 1;
                    break;
                case 1: // 경사로 아래
                    if (flatCnt < 0) {
                        return false;
                    }
                    flatCnt = 1 - L;
                    break;
                case 0: // 평지
                    flatCnt++;

                    break;
                default:
                    return false;
            }
            before = road[i];
        }

        if (flatCnt < 0) {
            return false;
        }

        return true;
    }

}
