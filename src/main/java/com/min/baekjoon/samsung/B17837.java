package main.java.com.min.baekjoon.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B17837 {
    static int N, K;
    static int[][] board;
    static Chess[] chesses;
    static List<Integer>[][] order;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        chesses = new Chess[K];
        order = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                order[i][j] = new ArrayList();
            }
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            Chess chess = new Chess(r - 1, c - 1, dir - 1);
            chesses[i] = chess;
            order[r - 1][c - 1].add(i);
        }
        int answer = 0;
        while (true) {
            answer++;
            if (turn()) {
                System.out.println(answer);
                return;
            }
            if (answer == 1000) {
                System.out.println(-1);
                return;
            }
        }
    }

    public static boolean turn() {
        for (int i = 0; i < K; i++) {
            int r = chesses[i].r;
            int c = chesses[i].c;
            int tmpR = r + dr[chesses[i].dir];
            int tmpC = c + dc[chesses[i].dir];

            if (tmpR < 0 || tmpR >= N || tmpC < 0 || tmpC >= N || board[tmpR][tmpC] == 2) { //방향 전환
                chesses[i].dir += chesses[i].dir % 2 == 0 ? 1 : -1;
                tmpR = r + dr[chesses[i].dir];
                tmpC = c + dc[chesses[i].dir];
            }

            if (tmpR < 0 || tmpR >= N || tmpC < 0 || tmpC >= N || board[tmpR][tmpC] == 2) {
                continue;
            }
            if (board[tmpR][tmpC] == 0) { // 흰색
                int idx = -1;
                for (int j = 0; j < order[r][c].size(); j++) {
                    int cur = order[r][c].get(j);
                    if (i == cur) {
                        idx = j;
                    }
                    if (idx == -1) {
                        continue;
                    }
                    order[tmpR][tmpC].add(cur);
                    chesses[cur].r = tmpR;
                    chesses[cur].c = tmpC;
                }
                int size = order[r][c].size();
                for (int j = idx; j < size; j++) {
                    order[r][c].remove(order[r][c].size() - 1);
                }
            } else if (board[tmpR][tmpC] == 1) { // 빨간색
                int idx = -1;
                for (int j = order[r][c].size() - 1; j >= 0; j--) {
                    int cur = order[r][c].get(j);
                    if (i == cur) {
                        idx = j;
                        break;
                    }
                }
                for (int j = order[r][c].size() - 1; j >= idx; j--) {
                    int cur = order[r][c].get(j);
                    order[tmpR][tmpC].add(cur);
                    chesses[cur].r = tmpR;
                    chesses[cur].c = tmpC;
                }
                int size = order[r][c].size();
                for (int j = idx; j < size; j++) {
                    order[r][c].remove(order[r][c].size() - 1);
                }
            }
            if (order[tmpR][tmpC].size() >= 4) {
                return true;
            }
        }

        return false;
    }

    public static class Chess {
        int r;
        int c;
        int dir;

        public Chess(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }
}
