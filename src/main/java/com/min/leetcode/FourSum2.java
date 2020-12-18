package com.min.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FourSum2 {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int a : A) {
            for (int b : B) {
                int count = map.getOrDefault(a + b, 0);
                map.put(a + b, count + 1);
            }
        }

        for (int c : C) {
            for (int d : D) {
                answer += map.getOrDefault(-(c + d), 0);
            }
        }

        return answer;
    }
}
