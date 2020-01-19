package com.min.baekjoon.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B13458 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N;
        int B, C;
        String testers;
        long answer = 0;

        N = Integer.parseInt(br.readLine());
        answer += N;
        testers = br.readLine();
        String[] splits = br.readLine().split(" ");
        B = Integer.parseInt(splits[0]);
        C = Integer.parseInt(splits[1]);

        StringTokenizer tester = new StringTokenizer(testers);
        while (tester.hasMoreTokens()) {
            long tmp = Integer.parseInt(tester.nextToken()) - B;
            if (tmp > 0) {
                answer += Math.ceil(tmp / (double) C);
            }
        }
        System.out.println(answer);
    }
}
