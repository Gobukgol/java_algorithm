package com.min.baekjoon.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14500 {
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    private static int N, M;
    private static int[][] board;
    private static int[][] visited;
    private static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        visited = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N * M; i++) {
            int r = i / M;
            int c = i % M;

            visited[r][c] = 1;
            dfs(r, c, 1, board[r][c]);
            visited[r][c] = 0;
            notDfsShape(r, c);
        }

        System.out.println(answer);
    }

    public static void dfs(int r, int c, int count, int sum) {
        if (count == 4) {
            answer = Math.max(answer, sum);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int tmpR = r + dr[i];
            int tmpC = c + dc[i];

            if (tmpR >= 0 && tmpR < N && tmpC >= 0 && tmpC < M && visited[tmpR][tmpC] == 0) {
                visited[tmpR][tmpC] = 1;
                dfs(tmpR, tmpC, count + 1, sum + board[tmpR][tmpC]);
                visited[tmpR][tmpC] = 0;
            }
        }
    }

    public static void notDfsShape(int r, int c) {
        int j;
        for (int i = 0; i < 4; i++) {
            int sum = board[r][c];
            for (j = 0; j < 4; j++) {
                if (j == i) {
                    continue;
                }
                int tmpR = r + dr[j];
                int tmpC = c + dc[j];

                if (tmpR < 0 || tmpR >= N || tmpC < 0 || tmpC >= M) {
                    break;
                }
                sum += board[tmpR][tmpC];
            }
            if (j == 4) {
                answer = Math.max(answer, sum);
            }
        }
    }
}
