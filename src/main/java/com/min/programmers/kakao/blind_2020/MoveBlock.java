package com.min.programmers.kakao.blind_2020;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//일부 통과, 일부 실패
public class MoveBlock {
    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}, {0, 1, 0, 1, 1}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}};
        System.out.println(solution(board));
    }

    public static int solution(int[][] board) {
        int answer = 10000;
        int size = board.length;
        int[][] newBoard = new int[size + 2][size + 2];
        for (int i = 0; i < size + 2; i++) {
            for (int j = 0; j < size + 2; j++) {
                newBoard[i][j] = 1;
            }
        }

        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                newBoard[i][j] = board[i - 1][j - 1];
            }
        }

        Queue<Robot> queue = new LinkedList<>();
        Robot first = new Robot();
        first.init(newBoard);
        queue.add(first);
        while (!queue.isEmpty()) {
            Robot current = queue.poll();
            if (current.isEnd(size)) {
                if (answer > current.getTime()) {
                    answer = current.getTime();
                }
            } else {
                queue.addAll(current.move());
                queue.addAll(current.rotate());
            }
        }

        return answer;
    }

    public static class Robot {
        private static final String HORIZONTAL = "horizontal";
        private static final String VERTICAL = "vertical";
        private String state;
        private boolean beforeRotate;
        private int r;
        private int c;
        private int time;
        private int[][] board;

        public Robot() {

        }

        public Robot(String state, int r, int c, int time, boolean beforeRotate, int[][] board) {
            this.state = state;
            this.r = r;
            this.c = c;
            this.time = time;
            this.beforeRotate = beforeRotate;
            this.board = board;
        }

        public void init(int board[][]) {
            this.time = 0;
            this.state = HORIZONTAL;
            this.r = 1;
            this.c = 1;
            this.beforeRotate = false;
            this.board = board;
        }

        public List<Robot> move() {
            List<Robot> available = new LinkedList<>();
            Robot moved;
            int[][] newBoard;
            if (isHorizontal()) {
                if (board[r][c + 1 + 1] == 0) {
                    newBoard = this.board;
                    newBoard[r][c] = 1;
                    moved = new Robot(HORIZONTAL, r, c + 1, time + 1, false, newBoard);
                    available.add(moved);
                }

                if (board[r + 1][c] == 0 && board[r + 1][c + 1] == 0) {
                    newBoard = this.board;
                    newBoard[r][c] = 1;
                    moved = new Robot(HORIZONTAL, r + 1, c, time + 1, false, newBoard);
                    available.add(moved);
                }

            } else {
                if (board[r + 1 + 1][c] == 0) {
                    newBoard = this.board;
                    newBoard[r][c] = 1;
                    moved = new Robot(VERTICAL, r + 1, c, time + 1, false, newBoard);
                    available.add(moved);
                }

                if (board[r][c + 1] == 0 && board[r + 1][c + 1] == 0) {
                    newBoard = this.board;
                    newBoard[r][c] = 1;
                    moved = new Robot(VERTICAL, r, c + 1, time + 1, false, newBoard);
                    available.add(moved);
                }
            }
//            System.out.println("move : "+available.size());
            return available;
        }

        public List<Robot> rotate() {
            List<Robot> available = new LinkedList<>();
            if (beforeRotate) {
                return available;
            }

            Robot rotated;
            int[][] newBoard;
            if (isHorizontal()) {
                //첫 번째 축
                if (board[r - 1][c + 1] == 0 && board[r - 1][c] == 0) { //반시계
                    newBoard = this.board;
                    newBoard[r][c] = 1;
                    rotated = new Robot(VERTICAL, r - 1, c, time + 1, true, newBoard);
                    available.add(rotated);
                }
                if (board[r + 1][c + 1] == 0 && board[r + 1][c] == 0) { //시계
                    newBoard = this.board;
                    newBoard[r][c] = 1;
                    rotated = new Robot(VERTICAL, r, c, time + 1, true, newBoard);
                    available.add(rotated);
                }
                //두 번째 축
                if (board[r + 1][c] == 0 && board[r + 1][c + 1] == 0) { //반시계
                    newBoard = this.board;
                    newBoard[r][c] = 1;
                    rotated = new Robot(VERTICAL, r, c + 1, time + 1, true, newBoard);
                    available.add(rotated);
                }
                if (board[r - 1][c] == 0 && board[r - 1][c + 1] == 0) { //시계
                    newBoard = this.board;
                    newBoard[r][c] = 1;
                    rotated = new Robot(VERTICAL, r - 1, c, time + 1, true, newBoard);
                    available.add(rotated);
                }

            } else {
                //첫 번째 축
                if (board[r + 1][c + 1] == 0 && board[r][c + 1] == 0) { //반시계
                    newBoard = this.board;
                    newBoard[r][c] = 1;
                    rotated = new Robot(HORIZONTAL, r, c, time + 1, true, newBoard);
                    available.add(rotated);
                }
                if (board[r + 1][c - 1] == 0 && board[r][c - 1] == 0) {
                    newBoard = this.board;
                    newBoard[r][c] = 1;
                    rotated = new Robot(HORIZONTAL, r, c - 1, time + 1, true, newBoard);
                    available.add(rotated);
                }
                //두 번째 축
                if (board[r][c - 1] == 0 && board[r + 1][c - 1] == 0) { //반시계
                    newBoard = this.board;
                    newBoard[r][c] = 1;
                    rotated = new Robot(HORIZONTAL, r + 1, c - 1, time + 1, true, newBoard);
                    available.add(rotated);
                }
                if (board[r][c + 1] == 0 && board[r + 1][c + 1] == 0) {
                    newBoard = this.board;
                    newBoard[r][c] = 1;
                    rotated = new Robot(HORIZONTAL, r + 1, c, time + 1, true, newBoard);
                    available.add(rotated);
                }
            }
//            System.out.println("rotate : "+available.size());
            return available;
        }

        public boolean isEnd(int boardSize) {
            if (isHorizontal()) {
                if (r == boardSize && c + 1 == boardSize) {
                    return true;
                }
            } else {
                if (r + 1 == boardSize && c == boardSize) {
                    return true;
                }
            }
            return false;
        }

        public boolean isHorizontal() {
            return state.equals(HORIZONTAL) ? true : false;
        }

        public int getTime() {
            return time;
        }
    }
}
