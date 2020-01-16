package com.min.baekjoon.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class B12100 {
    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;
    private static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] table = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] splits = br.readLine().split(" ");
            for (int j = 0; j < splits.length; j++) {
                int num = Integer.parseInt(splits[j]);
                table[i][j] = num;

            }
        }
        dfs(0, table);
        System.out.println(answer);
    }

    public static void dfs(int count, int[][] table) {
        int max = findMax(table);
        if (answer < max) {
            answer = max;
        }
        if (count == 5) {
            return;
        }
        for (int i = 0; i < 4; i++) {
            int[][] newTable = move(table, i);
            dfs(count + 1, newTable);
        }
    }

    public static int[][] move(int[][] table, int dir) {
        int N = table.length;
        int[][] newTable = new int[N][N];

        Queue<Integer> q = new LinkedList();
        switch (dir) {
            case UP:
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (table[j][i] != 0) {
                            q.add(table[j][i]);
                        }
                    }
                    int idx = 0;
                    while (!q.isEmpty()) {
                        int num = q.poll();
                        if (newTable[idx][i] == 0) {
                            newTable[idx][i] = num;
                        } else if (newTable[idx][i] == num) {
                            newTable[idx++][i] += num;
                        } else {
                            newTable[++idx][i] = num;
                        }
                    }
                }
                break;
            case DOWN:
                for (int i = 0; i < N; i++) {
                    for (int j = N - 1; j >= 0; j--) {
                        if (table[j][i] != 0) {
                            q.add(table[j][i]);
                        }
                    }
                    int idx = N - 1;
                    while (!q.isEmpty()) {
                        int num = q.poll();
                        if (newTable[idx][i] == 0) {
                            newTable[idx][i] = num;
                        } else if (newTable[idx][i] == num) {
                            newTable[idx--][i] += num;
                        } else {
                            newTable[--idx][i] = num;
                        }
                    }
                }
                break;
            case LEFT:
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (table[i][j] != 0) {
                            q.add(table[i][j]);
                        }
                    }
                    int idx = 0;
                    while (!q.isEmpty()) {
                        int num = q.poll();
                        if (newTable[i][idx] == 0) {
                            newTable[i][idx] = num;
                        } else if (newTable[i][idx] == num) {
                            newTable[i][idx++] += num;
                        } else {
                            newTable[i][++idx] = num;
                        }
                    }
                }
                break;
            case RIGHT:
                for (int i = 0; i < N; i++) {
                    for (int j = N - 1; j >= 0; j--) {
                        if (table[i][j] != 0) {
                            q.add(table[i][j]);
                        }
                    }
                    int idx = N - 1;
                    while (!q.isEmpty()) {
                        int num = q.poll();
                        if (newTable[i][idx] == 0) {
                            newTable[i][idx] = num;
                        } else if (newTable[i][idx] == num) {
                            newTable[i][idx--] += num;
                        } else {
                            newTable[i][--idx] = num;
                        }
                    }
                }
                break;
        }

        return newTable;
    }

    public static int findMax(int[][] table) {
        int max = Integer.MIN_VALUE;
        int N = table.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (max < table[i][j]) {
                    max = table[i][j];
                }
            }
        }
        return max;
    }

    public static void copyTable(int[][] source, int[][] dest) {
        int N = source.length;
        for (int i = 0; i < N; i++) {
            System.arraycopy(source[i], 0, dest, 0, N);
        }
    }

    public static void printTable(int[][] table) {
        int N = table.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }
}
