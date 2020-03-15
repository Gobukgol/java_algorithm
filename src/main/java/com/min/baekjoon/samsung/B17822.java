package main.java.com.min.baekjoon.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B17822 {
    static int N, M, T;
    static int circles[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        circles = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                circles[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int x, d, k;
            x = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            cycle(x, d, k);
//            System.out.println("cycle");
//            print();
            delete();
//            System.out.println("delete");
//            print();
        }

        int[] answer = getCntAndSum();
        System.out.println(answer[1]);
    }

    public static void print() {
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(circles[i]));
        }
    }

    public static void cycle(int x, int d, int k) {
        for (int i = x; i <= N; i += x) {
            circular(i - 1, d, k);
        }
    }

    public static void delete() {
        boolean[][] check = new boolean[N][M];
        boolean tmp = false;
        //위 아래 검사
        for (int j = 0; j < M; j++) {
            for (int i = 0; i < N - 1; i++) {
                if (circles[i][j] != 0 && circles[i][j] == circles[i + 1][j]) {
                    tmp = true;
                    check[i][j] = true;
                    check[i + 1][j] = true;
                }
            }
        }

        // 좌 우 검사
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (j == M - 1) {
                    if (circles[i][j] != 0 && circles[i][j] == circles[i][0]) {
                        check[i][j] = true;
                        check[i][0] = true;
                        tmp = true;
                    }
                } else {
                    if (circles[i][j] != 0 && circles[i][j] == circles[i][j + 1]) {
                        check[i][j] = true;
                        check[i][j + 1] = true;
                        tmp = true;
                    }
                }
            }
        }

        if (tmp) { // 같은게 하나라도 있다면
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (check[i][j]) {
                        circles[i][j] = 0;
                    }
                }
            }
            return;
        }

        int[] result = getCntAndSum();
        double average = (double) result[1] / result[0];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (circles[i][j] == 0) {
                    continue;
                }
                if (circles[i][j] > average) {
                    circles[i][j] += -1;
                } else if (circles[i][j] < average) {
                    circles[i][j] += 1;
                }
            }
        }
    }

    public static int[] getCntAndSum() {
        int[] result = {0, 0};
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (circles[i][j] != 0) {
                    result[0]++;
                    result[1] += circles[i][j];
                }
            }
        }

        return result;
    }


    public static void circular(int num, int d, int cnt) { //시계 방향으로 cnt 회전, 반시계 방향 회전은 시계방향 3번 회전과 같음
        int realCnt = cnt % M; // M번 돌리면 제자리

        if (d == 0) { // 시계
            for (int i = 0; i < realCnt; i++) {
                int tmp = circles[num][M - 1];
                for (int j = M - 1; j > 0; j--) {
                    circles[num][j] = circles[num][j - 1];
                }
                circles[num][0] = tmp;
            }
        } else {
            for (int i = 0; i < realCnt; i++) {
                int tmp = circles[num][0];
                for (int j = 0; j < M - 1; j++) {
                    circles[num][j] = circles[num][j + 1];
                }
                circles[num][M - 1] = tmp;
            }
        }
    }

}
