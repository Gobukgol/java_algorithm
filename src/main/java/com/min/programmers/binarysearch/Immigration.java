package com.min.programmers.binarysearch;

import java.util.Arrays;

public class Immigration {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long min = 0;
        long max = times[times.length - 1] * (long) n;
        long answer = max;

        while (min <= max) {
            long mid = (min + max) / 2;
            int cnt = 0;
            for (int i = 0; i < times.length; i++) {
                cnt += mid / times[i];
            }

            if (cnt < n) {
                min = mid + 1;
            } else {
                answer = Math.min(answer, mid);
                max = mid - 1;
            }
        }

        return answer;
    }
}
