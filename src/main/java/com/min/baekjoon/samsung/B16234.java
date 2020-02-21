package main.java.com.min.baekjoon.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16234 {
    static int N, L, R;
    static Country[][] land;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[][] visited;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        land = new Country[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                land[i][j] = new Country(i, j, Integer.parseInt(st.nextToken()));
            }
        }

        bfs();

        System.out.println(answer);
    }


    public static void bfs() {
        List<Country> unify = new ArrayList<>();
        Queue<Country> queue = new LinkedList<>();


        while (true) {
            boolean flag = false;

            for (int i = 0; i < N; i++) {
                Arrays.fill(visited[i], false);
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {

                    if (visited[i][j]) {
                        continue;
                    }

                    visited[i][j] = true;

                    int populCnt = 0;
                    int countryCnt = 0;

                    for (int k = 0; k < 4; k++) {
                        int tmpR = i + dr[k];
                        int tmpC = j + dc[k];

                        if (tmpR < 0 || tmpR == N || tmpC < 0 || tmpC == N) {
                            continue;
                        }

                        int populDiff = Math.abs(land[i][j].popluation - land[tmpR][tmpC].popluation);

                        if (populDiff < L || populDiff > R) {
                            continue;
                        }
                        if (visited[tmpR][tmpC]) {
                            continue;
                        }
                        //다음꺼 진행
                        populCnt += land[tmpR][tmpC].popluation;
                        countryCnt++;
                        queue.add(land[tmpR][tmpC]);
                        unify.add(land[tmpR][tmpC]);
                        visited[tmpR][tmpC] = true;
                    }

                    if (countryCnt != 0) {
                        unify.add(land[i][j]);
                        countryCnt++;
                        populCnt += land[i][j].popluation;
                    }

                    while (!queue.isEmpty()) {
                        Country cur = queue.poll();
                        //visited[cur.r][cur.c] = true;
                        for (int k = 0; k < 4; k++) {
                            int tmpR = cur.r + dr[k];
                            int tmpC = cur.c + dc[k];

                            if (tmpR < 0 || tmpR == N || tmpC < 0 || tmpC == N) {
                                continue;
                            }

                            int populDiff = Math.abs(cur.popluation - land[tmpR][tmpC].popluation);

                            if (populDiff < L || populDiff > R) {
                                continue;
                            }
                            if (visited[tmpR][tmpC]) {
                                continue;
                            }
                            //다음꺼 진행
                            populCnt += land[tmpR][tmpC].popluation;
                            countryCnt++;
                            queue.add(land[tmpR][tmpC]);
                            unify.add(land[tmpR][tmpC]);
                            visited[tmpR][tmpC] = true;
                        }
                    }


                    if (unify.size() != 0) {
                        flag = true;
                        for (Country country : unify) {
                            country.popluation = populCnt / countryCnt;
                        }
                        unify.clear();
                    }
                }
            }

            if (!flag) {
                return;
            }
            answer++;
        }
    }

    public static class Country {
        int r;
        int c;
        int popluation;

        public Country(int r, int c, int popluation) {
            this.r = r;
            this.c = c;
            this.popluation = popluation;
        }

        @Override
        public String toString() {
            return "Country{" +
                    "r=" + r +
                    ", c=" + c +
                    ", popluation=" + popluation +
                    '}';
        }
    }
}