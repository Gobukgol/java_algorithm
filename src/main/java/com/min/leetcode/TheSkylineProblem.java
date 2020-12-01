package com.min.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class TheSkylineProblem {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Point> points = new ArrayList<>();

        for (int[] building : buildings) {
            points.add(new Point(building[0], building[2], true));
            points.add(new Point(building[1], building[2], false));
        }

        Collections.sort(points);

        PriorityQueue<Integer> heights = new PriorityQueue(Comparator.reverseOrder());
        heights.add(0);
        int maxValue = 0;

        for (Point cur : points) {
            if (cur.isStart()) {
                heights.add(cur.getY());
            } else {
                heights.remove(cur.getY());
            }
            int maxTemp = heights.peek();

            if (maxTemp != maxValue) {
                answer.add(Arrays.asList(cur.x, maxTemp));
                maxValue = maxTemp;
            }
        }

        return answer;
    }

    class Point implements Comparable<Point> {
        private int x;
        private int y;
        private boolean isStart;

        public Point(final int x, final int y, final boolean isStart) {
            this.x = x;
            this.y = y;
            this.isStart = isStart;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public boolean isStart() {
            return isStart;
        }

        @Override
        public int compareTo(final Point o) {
            if (this.x == o.x) {
                if (isStart && o.isStart) {
                    return -Integer.compare(y, o.y);
                } else if (!isStart && !o.isStart) {
                    return Integer.compare(y, o.y);
                } else {
                    if (isStart) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            }

            return Integer.compare(x, o.x);
        }
    }
}
