package main.java.com.min.baekjoon.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B17142 {
    static int[][] lab;
    static int N, M;
    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, -1, 1};
    static int answer = Integer.MAX_VALUE;
    static List<Virus> viruses;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        lab = new int[N][N];
        viruses = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int tmp = Integer.parseInt(st.nextToken());

                if (tmp == 2) {
                    viruses.add(Virus.valueOf(i, j, 0));
                    tmp = -2; //비활성 바이러스
                } else if (tmp == 1) {
                    tmp = -1; // 벽
                }

                lab[i][j] = tmp;
            }
        }

        boolean visited[] = new boolean[viruses.size()];

        comb(visited, 0, 0);

        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        System.out.println(answer);
    }

    public static int spread(Queue<Virus> q, int[][] copy, boolean[][] visited) {
        int totalTime = -1;
        while (!q.isEmpty()) {
            Virus cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int r = cur.r + dr[i];
                int c = cur.c + dc[i];
                int time = cur.time + 1;

                if (r < 0 || r >= N || c < 0 || c >= N) {
                    continue;
                }

                if (copy[r][c] == -1 || visited[r][c]) {
                    continue;
                }

                visited[r][c] = true;

                if (copy[r][c] == -2) { // 비활성 바이러스 있는 곳
                    q.add(Virus.valueOf(r, c, time));
                    continue;
                }
                copy[r][c] = time;
                q.add(Virus.valueOf(r, c, time));
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (copy[i][j] == 0 && !visited[i][j]) {
                    return -1;
                }
                totalTime = Math.max(totalTime, copy[i][j]);
            }
        }

        return totalTime;
    }

    public static void comb(boolean visited[], int cnt, int next) {
        if (cnt == M) {
            int[][] copy = new int[N][N];

            for (int i = 0; i < N; i++) {
                copy[i] = lab[i].clone();
            }

            Queue<Virus> q = new LinkedList<>();
            boolean[][] visitCopy = new boolean[N][N];
            for (int i = 0; i < visited.length; i++) {
                if (visited[i]) {
                    q.add(viruses.get(i));
                    copy[viruses.get(i).r][viruses.get(i).c] = 0;
                    visitCopy[viruses.get(i).r][viruses.get(i).c] = true;
                }
            }

            int result = spread(q, copy, visitCopy);
            if (result != -1) {
                answer = Math.min(answer, result);
            }
            return;
        }

        for (int i = next; i < visited.length; i++) {
            visited[i] = true;
            comb(visited, cnt + 1, i + 1);
            visited[i] = false;
        }
    }

    static class Virus {
        int r;
        int c;
        int time;

        private Virus(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }

        public static Virus valueOf(int r, int c, int time) {
            return new Virus(r, c, time);
        }
    }
}
