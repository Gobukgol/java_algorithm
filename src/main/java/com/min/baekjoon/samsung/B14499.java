package com.min.baekjoon.samsung;

import java.util.Scanner;

public class B14499 {
    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {1, -1, 0, 0};
    static int N, M, K, x, y;
    static int[][] map;
    static int[] dice;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] splits = scanner.nextLine().split(" ");
        N = Integer.parseInt(splits[0]);
        M = Integer.parseInt(splits[1]);
        map = new int[N][M];
        dice = new int[6];
        x = Integer.parseInt(splits[2]);
        y = Integer.parseInt(splits[3]);
        K = Integer.parseInt(splits[4]);

        for (int i = 0; i < N; i++) {
            splits = scanner.nextLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(splits[j]);
            }
        }

        splits = scanner.nextLine().split(" ");

        for (int i = 0; i < K; i++) {
            move(Integer.parseInt(splits[i]));
        }
    }

    public static void move(int dir) {
        int tmpX = x;
        int tmpY = y;

        tmpX += dx[dir - 1];
        tmpY += dy[dir - 1];

        if (tmpX == N || tmpX == -1 || tmpY == M || tmpY == -1) {
            return;
        }

        shuffle(dir);

        x = tmpX;
        y = tmpY;

        if (map[x][y] != 0) {
            dice[0] = map[x][y];
            map[x][y] = 0;
        } else {
            map[x][y] = dice[0];
        }

        System.out.println(dice[5]);
    }

    public static void shuffle(int dir) {
        int top = dice[5];
        int bottom = dice[0];
        int tmp = 1;
        if (dir % 2 == 0) {
            tmp = -1;
        }
        dice[0] = dice[dir];
        dice[5] = dice[dir + tmp];
        dice[dir] = top;
        dice[dir + tmp] = bottom;
    }
}
