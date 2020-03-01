package main.java.com.min.baekjoon.samsung;

import sun.nio.ch.IOStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B17140 {
    static int R, C, K;
    static int[][] A = new int[100][100];
    static int cR = 3, cC = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = 0;
        while (true) {
            if (A[R - 1][C - 1] > 100) {
                System.out.println(-1);
                return;
            } else if (A[R - 1][C - 1] == K) {
                System.out.println(answer);
                return;
            } else if (answer > 100) {
                System.out.println(-1);
                return;
            }
            if (cR >= cC) {
                R();
            } else {
                C();
            }
//            System.out.println(cR + " : " + cC);
//            for (int i = 0 ; i < cR ; i++) {
//                for (int j = 0; j < cC ; j++) {
//                    System.out.print(A[i][j]);
//                }
//                System.out.println();
//            }
            answer++;
        }
    }

    public static void R() {
        int max = Integer.MIN_VALUE;
        PriorityQueue<Number> pq = new PriorityQueue<>();
        for (int i = 0; i < cR; i++) {
            int[] numbers = new int[101];
            for (int j = 0; j < cC; j++) {
                if (A[i][j] == 0) {
                    continue;
                }
                numbers[A[i][j]]++;
            }

            for (int j = 1; j < 101; j++) {
                if (numbers[j] != 0) {
                    pq.add(new Number(j, numbers[j]));
                }
            }
            int j = 0;
            while (!pq.isEmpty()) {
                Number number = pq.poll();
                A[i][j++] = number.num;
                if (j == 100) {
                    break;
                }
                A[i][j++] = number.count;
                if (j == 100) {
                    break;
                }
            }
            pq.clear();

            max = Math.max(max, j);

            for (; j < 100; j++) {
                A[i][j] = 0;
            }
        }
        cC = max;
    }

    public static void C() {
        int max = Integer.MIN_VALUE;
        PriorityQueue<Number> pq = new PriorityQueue<>();
        for (int j = 0; j < cC; j++) {
            int[] numbers = new int[101];
            for (int i = 0; i < cR; i++) {
                if (A[i][j] == 0) {
                    continue;
                }
                numbers[A[i][j]]++;
            }

            for (int i = 1; i < 101; i++) {
                if (numbers[i] != 0) {
                    pq.add(new Number(i, numbers[i]));
                }
            }
            int i = 0;
            while (!pq.isEmpty()) {
                Number number = pq.poll();

                A[i++][j] = number.num;
                if (i == 100) {
                    break;
                }
                A[i++][j] = number.count;
                if (i == 100) {
                    break;
                }
            }
            pq.clear();
            max = Math.max(max, i);

            for (; i < 100; i++) {
                A[i][j] = 0;
            }
        }
        cR = max;
    }

    static class Number implements Comparable {
        int num;
        int count;

        public Number(int num, int count) {
            this.num = num;
            this.count = count;
        }

        @Override
        public int compareTo(Object o) {
            int result = 0;
            Number number2 = (Number) o;
            if (this.count == number2.count) {
                if (this.num > number2.num) {
                    result = 1;
                } else {
                    result = -1;
                }
            } else if (this.count > number2.count) {
                result = 1;
            } else {
                result = -1;
            }
            return result;
        }
    }
}
