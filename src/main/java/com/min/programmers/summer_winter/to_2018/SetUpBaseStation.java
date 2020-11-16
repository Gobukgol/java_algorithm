package com.min.programmers.summer_winter.to_2018;

public class SetUpBaseStation {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        int start = 0;
        int waveRange = w * 2 + 1;

        for (int i = 0; i < stations.length; i++) {
            int stLeft = stations[i] - 1 - w;
            int stRight = stations[i] - 1 + w;

            if (start >= stLeft && start <= stRight) {
                start = stRight + 1;
                continue;
            }

            int range = stLeft - start;
            if (range % waveRange > 0) {
                answer += 1;
            }

            answer += range / waveRange;

            start = stRight + 1;
        }

        if (start < n) {
            int range = n - start;

            if (range % waveRange > 0) {
                answer += 1;
            }

            answer += range / waveRange;
        }

        return answer;
    }
}
