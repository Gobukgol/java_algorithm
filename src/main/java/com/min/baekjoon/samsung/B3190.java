package com.min.baekjoon.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class B3190 {
    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    private static final int APPLE = 4;

    static int N, K, L;
    static int[][] board;
    static int answer = 0;
    static Queue<String[]> directions;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        board = new int[N][N];
        for (int i = 0; i < K; i++) {
            String[] splits = br.readLine().split(" ");
            int r = Integer.parseInt(splits[0]) - 1;
            int c = Integer.parseInt(splits[1]) - 1;
            board[r][c] = APPLE;
        }

        L = Integer.parseInt(br.readLine());
        directions = new LinkedList<>();
        Snake snake = new Snake();
        for (int i = 0; i < L; i++) {
            String[] splits = br.readLine().split(" ");
            directions.add(splits);
        }
        br.close();
        snake.move();
        System.out.println(answer);
    }


    public static class Snake {
        int length;
        int headDir;
        List<Body> bodies;

        public Snake() {
            length = 1;
            headDir = RIGHT;
            bodies = new ArrayList<>();
            bodies.add(new Body(0, 0));
        }

        public void move() {
            while (true) {
                answer++;

                Body head = bodies.get(length - 1);
                int headR = head.r + dr[headDir];
                int headC = head.c + dc[headDir];
                bodies.add(new Body(headR, headC));
                length++;

                if (isCrash()) {
                    return;
                }

                if (board[headR][headC] == APPLE) {
                    board[headR][headC] = 0;
                } else {
                    length--;
                    bodies.remove(0);
                }

                if (!directions.isEmpty()) {
                    String[] direction = directions.peek();
                    if (answer == Integer.parseInt(direction[0])) {
                        if (direction[1].equals("D")) {
                            headDir += 1;
                            if (headDir == 4) {
                                headDir = UP;
                            }
                        } else {
                            headDir -= 1;
                            if (headDir == -1) {
                                headDir = LEFT;
                            }
                        }
                        directions.poll();
                    }
                }
            }
        }

        public boolean isCrash() {
            Body head = bodies.get(length - 1);

            int size = bodies.stream().filter(body -> body.r == head.r && body.c == head.c)
                    .collect(Collectors.toList()).size();
            if (head.r == N || head.r == -1 || head.c == N || head.c == -1) {
                return true;
            } else if (size > 1) {
                return true;
            } else {
                return false;
            }
        }

        public static class Body {
            int r;
            int c;

            public Body(int r, int c) {
                this.r = r;
                this.c = c;
            }
        }
    }
}
