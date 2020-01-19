package com.min.baekjoon.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B14502 {
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    private static int N, M;
    private static int[][] lab;
    private static int answer = Integer.MIN_VALUE;
    private static List<int[]> virusies;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lab = new int[N][M];
        virusies = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());

                if (lab[i][j] == 2) {
                    int[] virus = new int[2];
                    virus[0] = i;
                    virus[1] = j;
                    virusies.add(virus);
                }
            }
        }
        dfs(0);
        System.out.println(answer);
    }

    public static void dfs(int wallCnt) {
        if (wallCnt == 3) {
            spread();
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (lab[i][j] == 0) {
                    lab[i][j] = 1;
                    dfs(wallCnt + 1);
                    lab[i][j] = 0;
                }
            }
        }

    }

    public static void spread() {
        Queue<int[]> q = new LinkedList<>();
        int[][] tmpLab = deepCopy(lab);
        q.addAll(virusies);
        while (!q.isEmpty()) {
            int[] virus = q.poll();

            for (int i = 0; i < 4; i++) {
                int[] tmp = new int[2];
                tmp[0] = virus[0] + dr[i];
                tmp[1] = virus[1] + dc[i];
                if (tmp[0] > -1 && tmp[0] < N && tmp[1] > -1 && tmp[1] < M && tmpLab[tmp[0]][tmp[1]] == 0) {
                    tmpLab[tmp[0]][tmp[1]] = 2;
                    q.add(tmp);
                }
            }
        }

        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tmpLab[i][j] == 0) {
                    count++;
                }
            }
        }
        answer = Math.max(answer, count);
    }

    public static int[][] deepCopy(int[][] original) {
        int n = original.length;
        int m = original[0].length;

        int[][] result = new int[n][m];

        for (int i = 0; i < n; i++) {
            System.arraycopy(original[i], 0, result[i], 0, original[i].length);
        }
        return result;
    }
}
