package com.min.baekjoon.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B14888 {
    private static long maxAnswer = Long.MIN_VALUE;
    private static long minAnswer = Long.MAX_VALUE;
    private static List<Integer> opts = new ArrayList<>();
    private static List<Long> oprs = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            oprs.add(Long.parseLong(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            for (int j = 0; j < tmp; j++) {
                opts.add(i);
            }
        }
        long first = oprs.get(0);
        oprs.remove(0);
        dfs(first);

        System.out.println(maxAnswer);
        System.out.println(minAnswer);

        br.close();
    }

    public static void dfs(long current) {
        if (opts.size() == 0) {
            maxAnswer = Long.max(maxAnswer, current);
            minAnswer = Long.min(minAnswer, current);
            return;
        }

        for (int i = 0; i < opts.size(); i++) {
            int tmpOpt = opts.get(i);
            long tmpOpr = oprs.get(0);
            oprs.remove(0);
            opts.remove(i);
            dfs(calc(current, tmpOpr, tmpOpt));
            oprs.add(0, tmpOpr);
            opts.add(i, tmpOpt);
        }
    }

    public static long calc(long o1, long o2, int opt) {
        long result = 0;

        switch (opt) {
            case 0:
                result = o1 + o2;
                break;
            case 1:
                result = o1 - o2;
                break;
            case 2:
                result = o1 * o2;
                break;
            case 3:
                result = o1 / o2;
                break;
        }

        return result;
    }
}
