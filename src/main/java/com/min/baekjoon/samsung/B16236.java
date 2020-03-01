package main.java.com.min.baekjoon.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16236 {
    static int N;
    static Element shark;
    static List<Element> preys;
    static int answer = 0;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int sizeUp = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        preys = new ArrayList<>();
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int cur = Integer.parseInt(st.nextToken());
                if (cur == 9) {
                    shark = Element.valueOf(i, j, 2);
                    continue;
                } else if (cur != 0) {
                    preys.add(Element.valueOf(i, j, cur));
                }
                map[i][j] = cur;
            }
        }
        while (true) {
            if (find()) {
                return;
            }
        }
    }

    public static boolean find() {
        PriorityQueue<Element> eatables = checkEatable();
        if (eatables.size() == 0) {
            System.out.println(answer);
            return true;
        }
        Element tmp = eatables.poll();
        if (tmp.distance == Integer.MAX_VALUE) {
            System.out.println(answer);
            return true;
        }
        eat(tmp);
        return false;
    }

    public static PriorityQueue<Element> checkEatable() {
        PriorityQueue<Element> eatables = new PriorityQueue();

        for (Element prey : preys) {
            if (prey.size < shark.size) {
                prey.setDistance();
                eatables.add(prey);
            }
        }

        return eatables;
    }

    public static void eat(Element prey) {
        int r = prey.r;
        int c = prey.c;

        answer += prey.distance;
        preys.remove(prey);
        sizeUp++;
        if (sizeUp % shark.size == 0) {
            shark.size++;
            sizeUp = 0;
        }

        move(r, c);
    }

    public static void move(int r, int c) {
        shark.r = r;
        shark.c = c;
    }

    public static class Element implements Comparable<Element> {
        int r;
        int c;
        int size;
        int distance;

        private Element(int r, int c, int size) {
            this.r = r;
            this.c = c;
            this.size = size;
        }

        public static Element valueOf(int r, int c, int size) {
            return new Element(r, c, size);
        }

        @Override
        public String toString() {
            return "Element{" +
                    "r=" + r +
                    ", c=" + c +
                    ", size=" + size +
                    ", distance=" + distance +
                    '}';
        }

        public int calcDistance(boolean visited[][]) {
            Queue<int[]> queue = new LinkedList<>();
            int min = Integer.MAX_VALUE;
            int[] first = new int[3];
            first[0] = shark.r;
            first[1] = shark.c;
            first[2] = 0;
            queue.add(first);
            visited[shark.r][shark.c] = true;
            while (!queue.isEmpty()) {
                int[] tmp = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int tmpR = tmp[0] + dr[i];
                    int tmpC = tmp[1] + dc[i];
                    int distance = tmp[2] + 1;
                    if (tmpR < 0 || tmpR >= N || tmpC < 0 || tmpC >= N) {
                        continue;
                    }
                    if (tmpR == r && tmpC == c) {
                        min = Math.min(min, distance);
                        continue;
                    }
                    if (visited[tmpR][tmpC]) {
                        continue;
                    }
                    if (map[tmpR][tmpC] > 0 && map[tmpR][tmpC] > shark.size) {
                        continue;
                    }

                    queue.add(new int[]{tmpR, tmpC, distance});
                    visited[tmpR][tmpC] = true;
                }
            }

            return min;
        }

        public void setDistance() {
            boolean[][] visited = new boolean[N][N];
            this.distance = calcDistance(visited);
        }

        @Override
        public int compareTo(Element o) {
            int result;
            if (distance == o.distance) {
                if (this.r == o.r) {
                    result = this.c < o.c ? -1 : 1;
                } else {
                    result = this.r < o.r ? -1 : 1;
                }
            } else {
                result = distance < o.distance ? -1 : 1;
            }

            return result;
        }
    }
}
