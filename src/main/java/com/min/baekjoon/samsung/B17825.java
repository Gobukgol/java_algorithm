package main.java.com.min.baekjoon.samsung;

import java.io.IOException;
import java.util.Scanner;

public class B17825 {
    static int answer = Integer.MIN_VALUE;
    static int[] dice = new int[10];
    static int[][] board = {
            {1, 2, 3, 4, 5}, // 0, 시작
            {2, 3, 4, 5, 6}, // 1
            {3, 4, 5, 6, 7}, // 2
            {4, 5, 6, 7, 8}, // 3
            {5, 6, 7, 8, 9}, // 4
            {10, 11, 12, 28, 29}, // 5
            {7, 8, 9, 13, 14}, // 6
            {8, 9, 13, 14, 15}, // 7
            {9, 13, 14, 15, 16}, // 8
            {13, 14, 15, 16, 17}, // 9
            {11, 12, 28, 29, 30}, // 10
            {12, 28, 29, 30, 31}, // 11
            {28, 29, 30, 31, 32}, // 12
            {18, 19, 28, 29, 30}, // 13
            {15, 16, 17, 20, 21}, // 14
            {16, 17, 20, 21, 22}, // 15
            {17, 20, 21, 22, 23}, // 16
            {20, 21, 22, 23, 24}, // 17
            {19, 28, 29, 30, 31}, // 18
            {28, 29, 30, 31, 32}, // 19
            {25, 26, 27, 28, 29}, // 20
            {22, 23, 24, 31, 32}, // 21
            {23, 24, 31, 32, 32}, // 22
            {24, 31, 32, 32, 32}, // 23
            {31, 32, 32, 32, 32}, // 24
            {26, 27, 28, 29, 30}, // 25
            {27, 28, 29, 30, 31}, // 26
            {28, 29, 30, 31, 32}, // 27
            {29, 30, 31, 32, 32}, // 28
            {30, 31, 32, 32, 32}, // 29
            {31, 32, 32, 32, 32}, // 30
            {32, 32, 32, 32, 32}, // 31
            {32, 32, 32, 32, 32}, // 32, 도착
    };
    static int[] score = { // 11개씩 끊어서 33개
            0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 13, // 0 ~ 10
            16, 19, 20, 22, 24, 26, 28, 22, 24, 30, 32, // 11 ~ 21
            34, 36, 38, 28, 27, 26, 25, 30, 35, 40, 0// 22 ~ 32
    };

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 10; i++) {
            dice[i] = scanner.nextInt();
        }
        int[] pieces = new int[10];
        setPieceChoice(0, pieces);

        System.out.println(answer);
    }

    public static void setPieceChoice(int cur, int[] pieces) {
        if (cur == 10) {
            //진행
            gameStart(pieces);
            return;
        }
        for (int i = 0; i < 4; i++) {
            pieces[cur] = i;
            setPieceChoice(cur + 1, pieces);
        }
    }

    public static void gameStart(int[] pieces) {
        int[] curPiecePos = new int[4];
        int[] curScore = new int[4];

        for (int i = 0; i < pieces.length; i++) {
            int pieceNum = pieces[i];
            int curDice = dice[i] - 1;
            int curPos = curPiecePos[pieceNum];
            int nextPos = board[curPos][curDice];

            for (int j = 0; j < curPiecePos.length; j++) {
                if (j != pieceNum) {
                    if (nextPos != 32 && nextPos == curPiecePos[j]) { //말이 곂치는 경우
                        return;
                    }
                }
            }

            curPiecePos[pieceNum] = nextPos;
            curScore[pieceNum] += score[nextPos];
        }
        int max = 0;
        for (int i = 0; i < curScore.length; i++) {
            max += curScore[i];
        }

        answer = Math.max(answer, max);
    }
}
