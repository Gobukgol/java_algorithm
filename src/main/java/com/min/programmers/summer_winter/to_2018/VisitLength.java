package main.java.com.min.programmers.summer_winter.to_2018;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class VisitLength {

    static class Solution {

        public int solution(String dirs) {
            int answer = 0;

            Map<Character, Point> dirMap = new HashMap<>();

            dirMap.put('U', new Point(-1, 0));
            dirMap.put('D', new Point(1, 0));
            dirMap.put('R', new Point(0, 1));
            dirMap.put('L', new Point(0, -1));

            Point gameChar = new Point(0, 0);

            Set<Edge> edges = new HashSet<>();

            for (int i = 0; i < dirs.length(); i++) {
                Point next = dirMap.get(dirs.charAt(i));

                int nR = gameChar.r + next.r;
                int nC = gameChar.c + next.c;

                if (nR > 5 || nR < -5 || nC > 5 || nC < -5) {
                    continue;
                }

                Point from = new Point(gameChar.r, gameChar.c);
                Point to = new Point(nR, nC);

                edges.add(new Edge(from, to));
                edges.add(new Edge(to, from));

                gameChar.r = nR;
                gameChar.c = nC;
            }

            return edges.size() / 2;
        }

        static class Edge {
            Point from;
            Point to;

            public Edge(Point from, Point to) {
                this.from = from;
                this.to = to;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Edge)) return false;
                Edge edge = (Edge) o;
                return (Objects.equals(from, edge.from) &&
                        Objects.equals(to, edge.to));
            }

            @Override
            public int hashCode() {
                return Objects.hash(from, to);
            }
        }

        static class Point {
            int r;
            int c;

            public Point(int r, int c) {
                this.r = r;
                this.c = c;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Point)) return false;
                Point point = (Point) o;
                return r == point.r &&
                        c == point.c;
            }

            @Override
            public int hashCode() {
                return Objects.hash(r, c);
            }
        }
    }
}
