package com.min.programmers.summer_winter.to_2018;

public class EditTopographic {
    public long solution(int[][] land, int P, int Q) {
        long answer = Long.MAX_VALUE;

        long max = Long.MIN_VALUE;
        long min = Long.MAX_VALUE;

        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[i].length; j++) {
                max = Math.max(max, land[i][j]);
                min = Math.min(min, land[i][j]);
            }
        }

        long mid = 0;

        while (min <= max) {
            mid = (max + min) / 2;

            if (max == min) {
                break;
            }

            long cost1 = calc(mid, land, P, Q);
            long cost2 = calc(mid + 1, land, P, Q);
            if (cost1 < cost2) {
                max = mid;
            } else if (cost1 > cost2) {
                min = mid + 1;
            } else {
                break;
            }
        }

        return calc(mid, land, P, Q);
    }

    private long calc(long height, int[][] land, int P, int Q) {
        long cost = 0;

        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[i].length; j++) {
                if (height < land[i][j]) {
                    cost += Math.abs(height - land[i][j]) * Q;
                } else if (height > land[i][j]) {
                    cost += Math.abs(height - land[i][j]) * P;
                }
            }
        }

        return cost;
    }
}
