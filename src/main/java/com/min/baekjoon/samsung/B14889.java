package com.min.baekjoon.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14889 {
    private static int N;
    private static int[][] abilityMap;
    private static int answer = Integer.MAX_VALUE;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        visited = new boolean[N];
        abilityMap = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int ability = Integer.parseInt(st.nextToken());
                if (i > j) {
                    abilityMap[j][i] += ability;
                } else {
                    abilityMap[i][j] = ability;
                }
            }
        }
        combination(visited, 0, N / 2);

        System.out.println(answer);

        br.close();
    }

    public static void combination(boolean visited[], int current, int r) {
        if (r == 0) {
            int start = 0, link = 0;
            int[] startTeam = new int[N / 2], linkTeam = new int[N / 2];
            for (int i = 0; i < N; i++) {
                if (visited[i]) {
                    startTeam[start++] = i;
                } else {
                    linkTeam[link++] = i;
                }
            }

            answer = Math.min(answer, Math.abs(getTeamAbility(startTeam) - getTeamAbility(linkTeam)));

            return;
        } else {
            for (int i = current; i < N; i++) {
                visited[i] = true;
                combination(visited, i + 1, r - 1);
                visited[i] = false;
            }
        }
    }

    public static int getTeamAbility(int[] teams) {
        int teamAbility = 0;

        for (int i = 0; i < teams.length; i++) {
            for (int j = i + 1; j < teams.length; j++) {
                teamAbility += abilityMap[teams[i]][teams[j]];
            }
        }

        return teamAbility;
    }
}
