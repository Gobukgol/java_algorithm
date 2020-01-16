package com.min.baekjoon;


import java.util.*;

public class b17143 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Shark> sharks = new ArrayList<>();

        int result = 0;

        String boardInfo = scanner.nextLine();
        String[] splits = boardInfo.split(" ");
        int R = Integer.parseInt(splits[0]);
        int C = Integer.parseInt(splits[1]);
        int M = Integer.parseInt(splits[2]);

        Shark[][] board = new Shark[R][C];

        for (int i = 0; i < M; i++) {
            String sharkInfo = scanner.nextLine();

            splits = sharkInfo.split(" ");
            int r = Integer.parseInt(splits[0]) - 1;
            int c = Integer.parseInt(splits[1]) - 1;
            int s = Integer.parseInt(splits[2]);
            int d = Integer.parseInt(splits[3]) - 1;
            int z = Integer.parseInt(splits[4]);
            Shark shark = new Shark(r, c, s, d, z, R, C);
            board[r][c] = shark;
            sharks.add(shark);
        }

        for (int i = 0; i < C; i++) {
            //잡고
            for (int j = 0; j < R; j++) {
                if (board[j][i] != null) {
                    result += board[j][i].z;
                    sharks.remove(board[j][i]);
                    board[j][i] = null;
                    break;
                }
            }

            if (sharks.size() == 0) {
                break;
            }

            //낚시터 초기화 해주고
            board = new Shark[R][C];

            //움직이고 먹히고
            for (Shark shark : sharks) {
                shark.move(board);
            }

            //먹힌애들 없애주고
            sharks.removeIf(shark -> shark.eated);

        }
        System.out.print(result);
    }

    public static class Shark {
        int rBound;
        int cBound;
        int r;
        int c;
        int s;
        int d;
        int z;
        boolean eated;

        public Shark(int r, int c, int s, int d, int z, int rBound, int cBound) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
            this.rBound = rBound;
            this.cBound = cBound;
            this.eated = false;
        }

        public void move(Shark[][] board) {

            if (this.eated) return;
            int dx[] = new int[]{-1, 1, 1, -1};
            int changePeri;
            switch (d) {
                case 0://위
                case 1://아래
                    changePeri = s % ((rBound - 1) * 2);
                    for (int i = 0; i < changePeri; i++) {
                        if (r == 0) {
                            d = 1;
                        } else if (r == rBound - 1) {
                            d = 0;
                        }
                        r += dx[d];
                    }
                    break;
                case 2://오른쪽
                case 3://왼쪽
                    changePeri = s % ((cBound - 1) * 2);
                    for (int i = 0; i < changePeri; i++) {
                        if (c == 0) {
                            d = 2;
                        } else if (c == cBound - 1) {
                            d = 3;
                        }
                        c += dx[d];
                    }
                    break;
            }

            if (board[r][c] == null) {
                board[r][c] = this;
            } else if (board[r][c].z < this.z) {
                board[r][c].eated = true;
                board[r][c] = this;
            } else {
                eated = true;
            }
        }
    }
}
