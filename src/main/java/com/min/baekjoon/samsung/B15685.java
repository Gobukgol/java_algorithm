package com.min.baekjoon.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class B15685 {
    private static final int COS = 0;
    private static final int SIN = 1;
    static boolean[][] map = new boolean[101][101];
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {1, 0, -1, 0};
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            makeDragonCurve(x, y, d, g);
        }

        getAnswer();
        System.out.println(answer);
    }

    public static void makeDragonCurve(int x, int y, int d, int g) {
        List<Pair> points = new LinkedList<>();
        points.add(Pair.of(x, y));
        points.add(Pair.of(x + dc[d], y + dr[d]));

        for (int i = 0; i < g; i++) {
            Pair last = points.get(points.size() - 1);
            List<Pair> temp = new LinkedList<>();
            for (int j = points.size() - 2; j >= 0; j--) {
                Pair result = rotate(points.get(j), last);
                temp.add(result);
            }
            points.addAll(temp);
        }

        for (Pair point : points) {
            map[point.y][point.x] = true;
        }
    }

    public static Pair rotate(Pair target, Pair datumPoint) {
        int xP = ((target.x - datumPoint.x) * COS - (target.y - datumPoint.y) * SIN) + datumPoint.x;
        int yP = ((target.x - datumPoint.x) * SIN + (target.y - datumPoint.y) * COS) + datumPoint.y;
        return Pair.of(xP, yP);
    }

    public static void getAnswer() {
        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                if (map[i][j]) {
                    dfs(1, i, j + 1);
                }
            }
        }
    }

    public static void dfs(int count, int nextR, int nextC) {
        if (nextR < 0 || nextR >= 101 || nextC < 0 || nextC >= 101) {
            return;
        }
        if (map[nextR][nextC]) {
            switch (count) {
                case 1:
                    dfs(count + 1, nextR + 1, nextC);
                    break;
                case 2:
                    dfs(count + 1, nextR, nextC - 1);
                    break;
                case 3:
                    dfs(count + 1, nextR - 1, nextC);
                    break;
                case 4:
                    answer++;
                    break;
            }
        }
        return;
    }

    static class Pair {
        final int x;
        final int y;

        private Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static Pair of(int x, int y) {
            return new Pair(x, y);
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
