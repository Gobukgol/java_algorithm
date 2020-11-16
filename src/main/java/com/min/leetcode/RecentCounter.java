package com.min.leetcode;

import java.util.ArrayList;
import java.util.List;

public class RecentCounter {
    private List<Integer> timeFrame;

    public RecentCounter() {
        timeFrame = new ArrayList<>();
    }

    public int ping(int t) {
        int minRange = t - 3000;

        timeFrame.add(t);

        return (int) timeFrame.stream()
                .filter(time -> inRange(minRange, t, time))
                .count();
    }

    private boolean inRange(int min, int max, int target) {
        return min <= target && max >= target;
    }
}
