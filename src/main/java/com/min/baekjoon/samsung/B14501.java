package com.min.baekjoon.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14501 {
    private static int N;
    private static int[] T, P;
    private static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = new int[N];
        P = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0, 0);
//      dp();
        System.out.println(answer);
    }

    public static void dp() {
        int[] dp = new int[N + 1];

        for (int i = 0; i < N; i++) {
            if (T[i] <= N) {
                dp[T[i]] = Math.max(dp[T[i]], dp[i] + P[i]);
                answer = Math.max(answer, dp[T[i]]);
            }
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
            answer = Math.max(answer, dp[i + 1]);
        }

    }

    public static void dfs(int time, int money) {

        if (time > N) {
            return;
        } else if (time == N) {
            answer = Math.max(answer, money);
            return;
        }
        //일했을때
        dfs(time + T[time], money + P[time]);
        //안했을때
        dfs(time + 1, money);
    }
}
