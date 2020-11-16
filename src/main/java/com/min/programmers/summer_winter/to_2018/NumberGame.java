package com.min.programmers.summer_winter.to_2018;

import java.util.Arrays;

public class NumberGame {
    public int solution(int[] A, int[] B) {
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        int aIdx = A.length - 1;
        int bIdx = B.length - 1;

        while (aIdx >= 0 && bIdx >= 0) {
            if (A[aIdx] < B[bIdx]) {
                answer++;
                aIdx--;
                bIdx--;
            } else {
                aIdx--;
            }
        }

        return answer;
    }
}
