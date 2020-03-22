package main.java.com.min.programmers.DP;

public class TileOrnament {
    public static void main(String[] args) {

    }

    static class Solution {
        public long solution(int N) {
            long answer;
            if (N == 1) {
                answer = 4;
            } else {
                long[] tiles = fibo(N);
                answer = (tiles[N - 1] + (tiles[N - 1] + tiles[N - 2])) * 2;
            }

            return answer;
        }

        public long[] fibo(int N) {
            long[] fibo = new long[N];
            fibo[0] = 1;
            fibo[1] = 1;

            for (int i = 2; i < N; i++) {
                fibo[i] = fibo[i - 1] + fibo[i - 2];
            }

            return fibo;
        }
    }
}
