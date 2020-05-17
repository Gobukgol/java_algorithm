package main.java.com.min.programmers.summer_winter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class LandMovement {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] land = new int[][]{{1, 4, 8, 10}, {5, 5, 5, 5}, {10, 10, 10, 10}, {10, 10, 10, 20}};
        int height = 1;

        System.out.println(solution.solution(land, height));
    }

    static class Solution {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        public int solution(int[][] land, int height) {
            int answer = 0;

            int[][] visited = new int[land.length][land[0].length];

            int districtCnt = bfs(land, height, visited);

            answer = mst(visited, districtCnt, land);

            return answer;
        }

        public int bfs(int[][] land, int height, int[][] visited) {
            int district = 0;
            for (int i = 0; i < visited.length; i++) {
                for (int j = 0; j < visited[i].length; j++) {

                    if (visited[i][j] > 0) {
                        continue;
                    }
                    visited[i][j] = ++district;
                    Queue<Tuple> q = new LinkedList<>();
                    q.add(new Tuple(i, j, land[i][j]));
                    while (!q.isEmpty()) {
                        Tuple cur = q.poll();

                        for (int k = 0; k < 4; k++) {
                            int nextR = cur.r + dr[k];
                            int nextC = cur.c + dc[k];
                            if (nextR < 0 || nextR >= land.length || nextC < 0 || nextC >= land[0].length) {
                                continue;
                            }

                            if (visited[nextR][nextC] > 0) {
                                continue;
                            }

                            if (Math.abs(cur.value - land[nextR][nextC]) > height) {
                                continue;
                            }

                            visited[nextR][nextC] = district;
                            q.add(new Tuple(nextR, nextC, land[nextR][nextC]));
                        }
                    }
                }
            }

            return district;
        }

        public int mst(int[][] visited, int districtCnt, int[][] land) {
            int answer = 0;
            List<Edge> edges = new ArrayList<>();

            for (int i = 0; i < visited.length; i++) {
                for (int j = 0; j < visited.length; j++) {
                    for (int k = 0; k < 4; k++) {
                        int nextR = i + dr[k];
                        int nextC = j + dc[k];

                        if (nextR < 0 || nextR >= land.length || nextC < 0 || nextC >= land[0].length) {
                            continue;
                        }

                        if (visited[i][j] == visited[nextR][nextC]) {
                            continue;
                        }

                        Edge edge = new Edge(visited[i][j], visited[nextR][nextC], Math.abs(land[i][j] - land[nextR][nextC]));

                        edges.add(edge);
                    }
                }
            }

            Collections.sort(edges);

            int[] districts = new int[districtCnt + 1];

            for (int i = 0; i < districts.length; i++) {
                districts[i] = i;
            }

            int cnt = 0;

            for (Edge edge : edges) {
                if (cnt == districtCnt) {
                    break;
                }

                int fromHead = findGraph(edge.from, districts);
                int toHead = findGraph(edge.to, districts);
                if (fromHead != toHead) {
                    if (fromHead < toHead) {
                        districts[toHead] = fromHead;
                    } else {
                        districts[fromHead] = toHead;
                    }
                    answer += edge.weight;
                    cnt++;
                }
            }

            return answer;
        }

        public int findGraph(int head, int[] districts) {
            if (districts[head] == head) {
                return head;
            }

            return findGraph(districts[head], districts);
        }

        public class Edge implements Comparable {
            int from;
            int to;
            int weight;

            public Edge(int from, int to, int weight) {
                this.from = from;
                this.to = to;
                this.weight = weight;
            }

            @Override
            public int compareTo(Object o) {
                Edge target = (Edge) o;

                int weightCompare = Integer.compare(weight, target.weight);

                if (weightCompare == 0) {
                    return Integer.compare(from, target.from);
                }

                return weightCompare;
            }
        }

        public class Tuple {
            int r;
            int c;
            int value;

            public Tuple(int r, int c, int value) {
                this.r = r;
                this.c = c;
                this.value = value;
            }
        }
    }
}
