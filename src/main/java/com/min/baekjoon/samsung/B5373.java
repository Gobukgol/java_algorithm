package main.java.com.min.baekjoon.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B5373 {
    static int[][][] cube = new int[6][3][3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                Arrays.fill(cube[i][j], i);
            }
        }

        for (int i = 0; i < n; i++) {
            int cnt = Integer.parseInt(br.readLine());
            String rotateWay = br.readLine();
            int[][][] copied = arrayCopy(cube);
            cubing(rotateWay, copied);

            // 출력
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    System.out.print(chageIntToChar(copied[0][r][c]));
                }
                System.out.println();
            }
        }
    }

    private static void cubing(String way, int[][][] copied) {
        StringTokenizer st = new StringTokenizer(way);
        while (st.hasMoreTokens()) {
            String rotate = st.nextToken();
//            System.out.println(rotate);
            int cnt = 1;
            if (rotate.charAt(1) == '-') {
                cnt = 3;
            }
            switch (rotate.charAt(0)) {
                case 'U':
                    U(copied);
                    for (int i = 0; i < cnt; i++) {
                        R(copied);
                    }
                    for (int i = 0; i < 3; i++) {
                        U(copied);
                    }
                    break;
                case 'D':
                    U(copied);
                    for (int i = 0; i < cnt; i++) {
                        L(copied);
                    }
                    for (int i = 0; i < 3; i++) {
                        U(copied);
                    }
                    break;
                case 'F':
                    F(copied);
                    for (int i = 0; i < cnt; i++) {
                        L(copied);
                    }
                    for (int i = 0; i < 3; i++) {
                        F(copied);
                    }
                    break;
                case 'B':
                    F(copied);
                    for (int i = 0; i < cnt; i++) {
                        R(copied);
                    }
                    for (int i = 0; i < 3; i++) {
                        F(copied);
                    }
                    break;
                case 'L':
                    for (int i = 0; i < cnt; i++) {
                        L(copied);
                    }
                    break;
                case 'R':
                    for (int i = 0; i < cnt; i++) {
                        R(copied);
                    }
                    break;
            }
        }
    }

    private static void U(int[][][] cube) { //주사위를 윗면 기준 오른쪽으로 굴림
        int[][] temp = arrayCopy(cube[0]);
        cube[0] = arrayCopy(cube[4]);
        cube[4] = arrayCopy(cube[1]);
        cube[1] = arrayCopy(cube[5]);
        cube[5] = temp;

        clock(cube[5], 1);
        clock(cube[1], 1);
        clock(cube[0], 1);
        clock(cube[2], 1);
        clock(cube[3], 3);
        clock(cube[4], 1);
    }

    private static void F(int[][][] cube) { //주사위를 앞면 기준 왼쪽으로 굴림
        int[][] temp = arrayCopy(cube[2]);
        cube[2] = arrayCopy(cube[5]);
        cube[5] = arrayCopy(cube[3]);
        cube[3] = arrayCopy(cube[4]);
        cube[4] = temp;

        clock(cube[0], 1);
        clock(cube[1], 3);
    }

    private static void L(int[][][] cube) {
        int[][] temp = arrayCopy(cube[2]);

        for (int i = 0; i < 3; i++) {
            cube[2][2 - i][0] = cube[0][2 - i][0];
            cube[0][2 - i][0] = cube[3][i][2];
            cube[3][i][2] = cube[1][2 - i][0];
            cube[1][2 - i][0] = temp[2 - i][0];
        }

        clock(cube[4], 1);
    }

    private static void R(int[][][] cube) {
        int[][] temp = arrayCopy(cube[3]);

        for (int i = 0; i < 3; i++) {
            cube[3][i][0] = cube[0][2 - i][2];
            cube[0][2 - i][2] = cube[2][2 - i][2];
            cube[2][2 - i][2] = cube[1][2 - i][2];
            cube[1][2 - i][2] = temp[i][0];
        }
        clock(cube[5], 1);
    }

    private static void clock(int[][] face, int cnt) {
        int size = face.length;

        for (int k = 0; k < cnt; k++) {
            int[][] tmp = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    tmp[j][size - 1 - i] = face[i][j];
                }
            }

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    face[i][j] = tmp[i][j];
                }
            }
        }
    }

    private static char chageIntToChar(int face) {
        char result = 'w';
        switch (face) {
            case 0:
                result = 'w';
                break;
            case 1:
                result = 'y';
                break;
            case 2:
                result = 'r';
                break;
            case 3:
                result = 'o';
                break;
            case 4:
                result = 'g';
                break;
            case 5:
                result = 'b';
                break;
        }

        return result;
    }

    private static int[][] arrayCopy(int[][] source) {
        int r = source.length;
        int c = source[0].length;

        int[][] copy = new int[r][c];

        for (int i = 0; i < r; i++) {
//            copy[i] = source[i].clone();
            for (int j = 0; j < c; j++) {
                copy[i][j] = source[i][j];
            }
        }

        return copy;
    }

    private static int[][][] arrayCopy(int[][][] source) {
        int f = source.length;
        int r = source[0].length;
        int c = source[0][0].length;

        int[][][] copy = new int[f][r][c];

        for (int i = 0; i < f; i++) {
            for (int j = 0; j < r; j++) {
                copy[i][j] = source[i][j].clone();
            }
        }

        return copy;
    }
}
