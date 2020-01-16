package com.min.baekjoon.samsung;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;


//통과했지만 메모리랑 시간이 너무 과함...

public class B13460 {
    public static void main(String[] args) {
        Queue<Game> queue = new LinkedList<>();
        Game game = init();
        queue.add(game);
        int answer = 11;
        boolean pass = true;
        while (!queue.isEmpty()) {
            Game current = queue.poll();
            int count = current.getCount();
            if (count == -1) {
                pass = false;
            } else if (count == 0) {
                Game moved = move(current, "up");
                if (moved != null) {
                    queue.add(moved);
                }
                moved = move(current, "down");
                if (moved != null) {
                    queue.add(moved);
                }
                moved = move(current, "left");
                if (moved != null) {
                    queue.add(moved);
                }
                moved = move(current, "right");
                if (moved != null) {
                    queue.add(moved);
                }
            } else {
                if (answer > count) {
                    answer = count;
                }
            }
        }
        if (!pass && answer == 11) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    public static Game init() {
        Game game = new Game();

        Scanner scanner = new Scanner(System.in);

        int N, M;
        String[] num = scanner.nextLine().split(" ");
        N = Integer.parseInt(num[0]);
        M = Integer.parseInt(num[1]);
        String[][] board = new String[N][M];

        for (int i = 0; i < N; i++) {
            String line = scanner.nextLine();
            String[] splits = line.split("");
            for (int j = 0; j < splits.length; j++) {
                board[i][j] = splits[j];
                if (splits[j].equals("R")) {
                    int[] position = {i, j};
                    game.setPositionR(position);
                }
                if (splits[j].equals("B")) {
                    int[] position = {i, j};
                    game.setPositionB(position);
                }
            }
        }

        game.setBoard(board);
        game.setCount(0);

        return game;
    }

    private static Game move(Game game, String dir) {
        Game moved = new Game();
        moved.board = deepCopy(game.board);
        moved.positionR = game.positionR.clone();
        moved.positionB = game.positionB.clone();
        moved.rClear = game.rClear;
        moved.bClear = game.bClear;
        moved.count = game.count;

        int rR = moved.positionR[0];
        int rC = moved.positionR[1];
        int bR = moved.positionB[0];
        int bC = moved.positionB[1];

        switch (dir) {
            case "up":
                if (moved.positionR[0] - moved.positionB[0] < 0) { //빨간 공이 위에 있는 경우
                    while (!moved.board[rR - 1][rC].equals("#")) {
                        if (moved.board[rR - 1][rC].equals("O")) {
                            moved.board[rR][rC] = ".";
                            rR--;
                            moved.rClear = true;
                            break;
                        }
                        moved.board[rR][rC] = ".";
                        rR--;
                        moved.board[rR][rC] = "R";
                    }

                    while (!moved.board[bR - 1][bC].equals("#") && !moved.board[bR - 1][bC].equals("R")) {
                        if (moved.board[bR - 1][bC].equals("O")) {
                            moved.board[bR][bC] = ".";
                            bR--;
                            moved.bClear = true;
                            break;
                        }
                        moved.board[bR][bC] = ".";
                        bR--;
                        moved.board[bR][bC] = "B";
                    }

                } else { //빨간 공이 아래
                    while (!moved.board[bR - 1][bC].equals("#")) {
                        if (moved.board[bR - 1][bC].equals("O")) {
                            moved.board[bR][bC] = ".";
                            bR--;
                            moved.bClear = true;
                            break;
                        }
                        moved.board[bR][bC] = ".";
                        bR--;
                        moved.board[bR][bC] = "B";
                    }

                    while (!moved.board[rR - 1][rC].equals("#") && !moved.board[rR - 1][rC].equals("B")) {
                        if (moved.board[rR - 1][rC].equals("O")) {
                            moved.board[rR][rC] = ".";
                            rR--;
                            moved.rClear = true;
                            break;
                        }
                        moved.board[rR][rC] = ".";
                        rR--;
                        moved.board[rR][rC] = "R";
                    }
                }
                break;
            case "down":
                if (moved.positionR[0] - moved.positionB[0] < 0) { //빨간 공이 위에 있는 경우
                    while (!moved.board[bR + 1][bC].equals("#")) {
                        if (moved.board[bR + 1][bC].equals("O")) {
                            moved.board[bR][bC] = ".";
                            bR++;
                            moved.bClear = true;
                            break;
                        }
                        moved.board[bR][bC] = ".";
                        bR++;
                        moved.board[bR][bC] = "B";
                    }

                    while (!moved.board[rR + 1][rC].equals("#") && !moved.board[rR + 1][rC].equals("B")) {
                        if (moved.board[rR + 1][rC].equals("O")) {
                            moved.board[rR][rC] = ".";
                            rR++;
                            moved.rClear = true;
                            break;
                        }
                        moved.board[rR][rC] = ".";
                        rR++;
                        moved.board[rR][rC] = "R";
                    }
                } else { //빨간 공이 아래
                    while (!moved.board[rR + 1][rC].equals("#")) {
                        if (moved.board[rR + 1][rC].equals("O")) {
                            moved.board[rR][rC] = ".";
                            rR++;
                            moved.rClear = true;
                            break;
                        }
                        moved.board[rR][rC] = ".";
                        rR++;
                        moved.board[rR][rC] = "R";
                    }
                    while (!moved.board[bR + 1][bC].equals("#") && !moved.board[bR + 1][bC].equals("R")) {
                        if (moved.board[bR + 1][bC].equals("O")) {
                            moved.board[bR][bC] = ".";
                            bR++;
                            moved.bClear = true;
                            break;
                        }
                        moved.board[bR][bC] = ".";
                        bR++;
                        moved.board[bR][bC] = "B";
                    }
                }
                break;
            case "left":
                if (moved.positionR[1] - moved.positionB[1] < 0) { // 빨간 공이 왼쪽
                    while (!moved.board[rR][rC - 1].equals("#")) {
                        if (moved.board[rR][rC - 1].equals("O")) {
                            moved.board[rR][rC] = ".";
                            rC--;
                            moved.rClear = true;
                            break;
                        }
                        moved.board[rR][rC] = ".";
                        rC--;
                        moved.board[rR][rC] = "R";
                    }

                    while (!moved.board[bR][bC - 1].equals("#") && !moved.board[bR][bC - 1].equals("R")) {
                        if (moved.board[bR][bC - 1].equals("O")) {
                            moved.board[bR][bC] = ".";
                            bC--;
                            moved.bClear = true;
                            break;
                        }
                        moved.board[bR][bC] = ".";
                        bC--;
                        moved.board[bR][bC] = "B";
                    }
                } else {
                    while (!moved.board[bR][bC - 1].equals("#")) {
                        if (moved.board[bR][bC - 1].equals("O")) {
                            moved.board[bR][bC] = ".";
                            bC--;
                            moved.bClear = true;
                            break;
                        }
                        moved.board[bR][bC] = ".";
                        bC--;
                        moved.board[bR][bC] = "B";
                    }
                    while (!moved.board[rR][rC - 1].equals("#") && !moved.board[rR][rC - 1].equals("B")) {
                        if (moved.board[rR][rC - 1].equals("O")) {
                            moved.board[rR][rC] = ".";
                            rC--;
                            moved.rClear = true;
                            break;
                        }
                        moved.board[rR][rC] = ".";
                        rC--;
                        moved.board[rR][rC] = "R";
                    }
                }
                break;
            case "right":
                if (moved.positionR[1] - moved.positionB[1] < 0) { // 빨간 공이 왼쪽
                    while (!moved.board[bR][bC + 1].equals("#")) {
                        if (moved.board[bR][bC + 1].equals("O")) {
                            moved.board[bR][bC] = ".";
                            bC++;
                            moved.bClear = true;
                            break;
                        }
                        moved.board[bR][bC] = ".";
                        bC++;
                        moved.board[bR][bC] = "B";
                    }

                    while (!moved.board[rR][rC + 1].equals("#") && !moved.board[rR][rC + 1].equals("B")) {
                        if (moved.board[rR][rC + 1].equals("O")) {
                            moved.board[rR][rC] = ".";
                            rC++;
                            moved.rClear = true;
                            break;
                        }
                        moved.board[rR][rC] = ".";
                        rC++;
                        moved.board[rR][rC] = "R";
                    }
                } else {
                    while (!moved.board[rR][rC + 1].equals("#")) {
                        if (moved.board[rR][rC + 1].equals("O")) {
                            moved.board[rR][rC] = ".";
                            rC++;
                            moved.rClear = true;
                            break;
                        }
                        moved.board[rR][rC] = ".";
                        rC++;
                        moved.board[rR][rC] = "R";
                    }
                    while (!moved.board[bR][bC + 1].equals("#") && !moved.board[bR][bC + 1].equals("R")) {
                        if (moved.board[bR][bC + 1].equals("O")) {
                            moved.board[bR][bC] = ".";
                            bC++;
                            moved.bClear = true;
                            break;
                        }
                        moved.board[bR][bC] = ".";
                        bC++;
                        moved.board[bR][bC] = "B";
                    }
                }
                break;
        }

        moved.positionR[0] = rR;
        moved.positionR[1] = rC;
        moved.positionB[0] = bR;
        moved.positionB[1] = bC;
        moved.count += 1;

        if (moved.positionR[0] == game.positionR[0] && moved.positionR[1] == game.positionR[1]) {
            if (moved.positionB[0] == game.positionB[0] && moved.positionB[1] == game.positionB[1]) {
                return null;
            }
        }
        return moved;
    }

    public static String[][] deepCopy(String[][] original) {
        int n = original.length;
        int m = original[0].length;

        String[][] result = new String[n][m];

        for (int i = 0; i < n; i++) {
            System.arraycopy(original[i], 0, result[i], 0, original[i].length);
        }
        return result;
    }

    public static class Game implements Cloneable {
        private String[][] board;
        private int count;
        private int[] positionR;
        private int[] positionB;
        private boolean rClear;
        private boolean bClear;

        public Game() {

        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        public int getCount() {
            if (bClear) {
                return -1;
            }

            if (rClear && bClear) {
                return -1;
            }
            if (count > 10) {
                return -1;
            }
            if (rClear) {
                return count;
            }
            return 0;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public void setBoard(String[][] board) {
            this.board = board;
        }

        public void setPositionR(int[] positionR) {
            this.positionR = positionR;
        }

        public void setPositionB(int[] positionB) {
            this.positionB = positionB;
        }
    }
}
