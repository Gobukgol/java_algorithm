package com.min.programmers.summer_winter.to_2018;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Delivery {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int N = 5;
        int[][] road = {{1, 2, 1}, {2, 3, 3}, {5, 2, 2}, {1, 4, 2}, {5, 3, 1}, {5, 4, 2}};
        int K = 3;
        System.out.println(solution.solution(N, road, K));
    }

    static class Solution {
        public int solution(int N, int[][] road, int K) {
            Queue<Edge> queue = new PriorityQueue<>();
            int[][] graph = new int[N][N];
            for (int i = 0; i < road.length; i++) {
                int from = road[i][0] - 1;
                int to = road[i][1] - 1;
                int time = road[i][2];
                if (graph[from][to] != 0) {
                    if (graph[from][to] > time) {
                        graph[from][to] = time;
                        graph[to][from] = time;
                    }
                } else {
                    graph[from][to] = time;
                    graph[to][from] = time;
                }
            }

            int[] dist = new int[N];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[0] = 0;
            queue.add(new Edge(0, 0));
            while (!queue.isEmpty()) {
                Edge edge = queue.poll();
                for (int i = 0; i < N; i++) {
                    if (edge.to == i) {
                        continue;
                    }

                    if (graph[edge.to][i] == 0) {
                        continue;
                    }

                    int time = graph[edge.to][i];
                    if (dist[i] > dist[edge.to] + time) {
                        dist[i] = dist[edge.to] + time;
                        queue.add(new Edge(i, dist[i]));
                    }
                }
            }
            int answer = 0;
            for (int i = 0; i < N; i++) {
                if (dist[i] <= K) {
                    answer++;
                }
            }

            return answer;
        }

        class Edge implements Comparable<Edge> {
            int to;
            int time;

            public Edge(int to, int time) {
                this.to = to;
                this.time = time;
            }

            @Override
            public int compareTo(Edge o) {
                return Integer.compare(time, o.time);
            }
        }
    }
}
