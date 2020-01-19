package com.min.baekjoon.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14503 {
    private static int N, M;
    private static int r, c, d;
    private static int[][] board;
    private static int[] dr = {-1, 0, 1, 0};
    private static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(clean());

        br.close();
    }

    public static int clean() {
        int answer = 0;

        while (true) {
            if (board[r][c] == 0) {
                answer++;
                board[r][c] = 2;
            }

            int i;
            for (i = 0; i < 4; i++) {
                d -= 1;
                if (d == -1) {
                    d = 3;
                }
                int tmpR = r + dr[d];
                int tmpC = c + dc[d];
                if (board[tmpR][tmpC] == 0) {
                    r = tmpR;
                    c = tmpC;
                    break;
                }
            }

            if (i == 4) {
                int tmpD = Math.abs(d + 2) % 4;
                int tmpR = r + dr[tmpD];
                int tmpC = c + dc[tmpD];

                if (board[tmpR][tmpC] == 1) {
                    break;
                }
                r = tmpR;
                c = tmpC;
            }
        }

        return answer;
    }
}
