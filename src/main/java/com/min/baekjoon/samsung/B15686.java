package com.min.baekjoon.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B15686 {
    static int N, M;
    static int[][] city;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        city = new int[N][N];

        List<Pair> chickens = new ArrayList<>();
        List<Pair> homes = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
                if (city[i][j] == 1) {
                    homes.add(Pair.of(i, j));
                } else if (city[i][j] == 2) {
                    chickens.add(Pair.of(i, j));
                }
            }
        }

        for (int i = 1; i <= M; i++) {
            getAnswer(i, chickens, homes);
        }

        System.out.println(answer);
    }

    public static void getAnswer(int selectCnt, List<Pair> chickens, List<Pair> homes) {
        List<boolean[]> combs = new ArrayList<>();
        boolean[] comb = new boolean[chickens.size()];
        combination(combs, comb, selectCnt, 0);

        for (boolean[] combi : combs) {
            int lengthSum = 0;
            for (Pair home : homes) {
                int min = Integer.MAX_VALUE;
                for (int i = 0; i < combi.length; i++) {
                    if (combi[i]) {
                        int length = calcLength(home, chickens.get(i));
                        min = Math.min(min, length);
                    }
                }
                lengthSum += min;
            }
            answer = Math.min(answer, lengthSum);
        }
    }

    public static void combination(List<boolean[]> combs, boolean[] comb, int curCnt, int start) {
        if (curCnt == 0) {
            combs.add(comb.clone());
            return;
        }

        for (int i = start; i < comb.length; i++) {
            comb[i] = true;
            combination(combs, comb, curCnt - 1, i + 1);
            comb[i] = false;
        }
    }

    public static int calcLength(Pair home, Pair chicken) {
        int result = Math.abs(home.r - chicken.r) + Math.abs(home.c - chicken.c);
        return result;
    }

    static class Pair {
        final int r;
        final int c;

        private Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public static Pair of(int r, int c) {
            return new Pair(r, c);
        }
    }
}
