package com.min.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

    public int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, (a1, a2) -> {
            if (a1[0] == a2[0]) {
                return Integer.compare(a1[1], a2[1]);
            }
            return Integer.compare(a1[0], a2[0]);
        });

        int left = intervals[0][0];
        int right = intervals[0][1];
        List<int[]> answers = new ArrayList<>();

        for (int i = 1; i < intervals.length; i++) {
            if (left <= intervals[i][0] && right >= intervals[i][1]) {
                right = Math.max(right, intervals[i][1]);
            } else {
                answers.add(new int[]{left, right});
                left = intervals[i][0];
                right = intervals[i][1];
            }
        }

        answers.add(new int[]{left, right});

        int[][] results = new int[answers.size()][2];

        for (int i = 0; i < answers.size(); i++) {
            results[i][0] = answers.get(i)[0];
            results[i][1] = answers.get(i)[1];
        }

        return results;
    }
}
