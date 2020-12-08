package com.min.leetcode;

import java.util.Arrays;

public class SprialMaxtrix2 {
    int[] dr = {0, -1, 0, 1};
    int[] dc = {1, 0, -1, 0};

    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(matrix[i], -1);
        }

        int r = 0, c = 0;
        int dir = 0;

        for (int i = 1; i <= n * n; i++) {
            matrix[r][c] = i;
            if (isRotate(matrix, dir, r, c, n)) {
                dir = (dir + 1) % 4;
            }

            r += dr[dir];
            c += dc[dir];
        }

        return matrix;
    }

    public boolean isRotate(int[][] matrix, int dir, int r, int c, int n) {
        int nR = r + dr[dir];
        int nC = c + dc[dir];

        return nR < 0 || nR >= n || nC < 0 || nC >= n || matrix[nR][nC] != -1;
    }
}
