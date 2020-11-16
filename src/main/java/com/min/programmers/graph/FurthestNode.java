package com.min.programmers.graph;

import java.util.LinkedList;
import java.util.Queue;

public class FurthestNode {
    public int solution(int n, int[][] edge) {
        int answer = 0;

        boolean map[][] = new boolean[n + 1][n + 1];
        int[] dist = new int[n + 1];

        for (int i = 0; i < edge.length; i++) {
            int from = edge[i][0];
            int to = edge[i][1];

            map[from][to] = true;
            map[to][from] = true;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        int maxDist = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int i = 2; i <= n; i++) {
                if (map[node][i] && dist[i] == 0) {
                    dist[i] = dist[node] + 1;
                    queue.add(i);
                    maxDist = Math.max(maxDist, dist[i]);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (maxDist == dist[i]) {
                answer++;
            }
        }

        return answer;
    }
}
