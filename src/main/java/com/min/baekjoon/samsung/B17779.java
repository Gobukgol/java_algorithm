package main.java.com.min.baekjoon.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B17779 {
    static int N;
    static int[][] A;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divide();

        System.out.println(answer);
    }

    public static void select(int x, int y, int d1, int d2) {
        int[] population = new int[5];
        int[][] district = new int[N][N];
        //5번 구역 초기화
        for (int i = 0; i <= d1; i++) { //1, 4 경계선
            int r = x + i;
            int c = y - i;

            district[r][c] = 5;
            district[r + d2][c + d2] = 5;
        }

        for (int i = 0; i <= d2; i++) { //2, 3 경계선
            int r = x + i;
            int c = y + i;

            district[r][c] = 5;
            district[r + d1][c - d1] = 5;
        }

        int first = -1;
        for (int i = 0; i < N; i++) { // 5번 내부
            for (int j = 0; j < N; j++) {
                if (district[i][j] == 5) {
                    if (first != -1) {
                        for (int k = first; k <= j; k++) {
                            district[i][k] = 5;
                        }
                    } else {
                        first = j;
                    }
                }
            }
            first = -1;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (district[i][j] == 5) {
                    population[4] += A[i][j];
                    continue;
                }
                //1번
                if (0 <= i && i < x + d1 && 0 <= j && j <= y) {
                    district[i][j] = 1;
                    population[0] += A[i][j];
                }
                //2번
                else if (0 <= i && i <= x + d2 && y < j && j < N) {
                    district[i][j] = 2;
                    population[1] += A[i][j];
                }
                //3번
                else if (x + d1 <= i && i < N && 0 <= j && j < y - d1 + d2) {
                    district[i][j] = 3;
                    population[2] += A[i][j];
                }
                //4번
                else if (x + d2 < i && j < N && y - d1 + d2 <= j && j < N) {
                    district[i][j] = 4;
                    population[3] += A[i][j];
                }
            }
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < population.length; i++) {
            max = Math.max(max, population[i]);
            min = Math.min(min, population[i]);
        }

        answer = Math.min(answer, max - min);
    }

    public static void divide() {
        int d1;
        int d2;
        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                d1 = 1;
                d2 = 1;
                while (true) {
                    if (x + d1 + d2 <= N && y - d1 >= 1 && y + d2 <= N) {
                        select(x - 1, y - 1, d1, d2);
                        d2++;
                    } else {
                        d2 = 1;
                        d1++;
                        if (!(x + d1 + d2 <= N && y - d1 >= 1 && y + d2 <= N)) {
                            break;
                        }
                    }
                }
            }
        }
    }
}
