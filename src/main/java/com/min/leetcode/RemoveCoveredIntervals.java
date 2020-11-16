package com.min.leetcode;


public class RemoveCoveredIntervals {
    public static void main(String[] args) {
        removeCoveredIntervals(new int[][]{{1, 2}, {1, 4}, {3, 4}});
    }

    public static int removeCoveredIntervals(int[][] intervals) {
        boolean[] deleted = new boolean[intervals.length];

        for (int i = 0; i < intervals.length - 1; i++) {
            if (deleted[i]) continue;
            for (int j = i + 1; j < intervals.length; j++) {
                if (deleted[j]) continue;

                if (isCovered(intervals[i], intervals[j])) {
                    deleted[j] = true;
                } else if (isCovered(intervals[j], intervals[i])) {
                    deleted[i] = true;
                    break;
                }
            }
        }

        int answer = 0;

        for (boolean isDeleted : deleted) {
            if (!isDeleted) {
                answer++;
            }
        }

        return answer;
    }

    private static boolean isCovered(int[] i1, int[] i2) {
        return i1[0] <= i2[0] && i1[1] >= i2[1];
    }
}
