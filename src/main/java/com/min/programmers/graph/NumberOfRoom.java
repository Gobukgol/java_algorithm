package com.min.programmers.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class NumberOfRoom {

    public int solution(int[] arrows) {
        int answer = 0;

        Set<Direction> directions = new HashSet<>();
        Set<Point> points = new HashSet<>();

        Point cur = new Point(0, 0);
        points.add(cur);

        for (int arrow : arrows) {
            for (int i = 0; i < 2; i++) {
                int pointSize = points.size();
                int dirSize = directions.size();

                Point next = cur.next(arrow);
                Direction direction = new Direction(cur, next);
                Direction reverse = new Direction(next, cur);

                if (points.contains(next) && !directions.contains(direction)) {
                    answer++;
                }

                points.add(next);
                directions.add(direction);
                directions.add(reverse);

                cur = next;
            }
        }

        return answer;
    }

    private static class Direction {
        private Point from;
        private Point to;

        public Direction(final Point from, final Point to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (!(o instanceof Direction)) return false;
            Direction direction = (Direction) o;
            return Objects.equals(from, direction.from) &&
                    Objects.equals(to, direction.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }

        @Override
        public String toString() {
            return "Direction{" +
                    "from=" + from.toString() +
                    ", to=" + to.toString() +
                    '}';
        }
    }

    private static class Point {
        private static final int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
        private static final int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

        int x;
        int y;

        public Point(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        public Point next(int arrow) {
            return new Point(x + dx[arrow], y + dy[arrow]);
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}

