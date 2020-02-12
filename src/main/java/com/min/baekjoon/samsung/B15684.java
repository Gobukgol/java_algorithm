package com.min.baekjoon.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B15684 {
    static int N, M, H;
    static int[][] loader;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        loader = new int[H][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            loader[a][b] = 1;
            loader[a][b + 1] = -1;
        }

        for (int i = 0; i <= 3; i++) {
            if (addBridge(0, i, 0, 0)) {
                System.out.println(i);
                return;
            }
        }

        System.out.println(-1);
        return;
    }

    public static boolean addBridge(int bridgeCnt, int wholeBridge, int sr, int sc) {
        if (bridgeCnt == wholeBridge) {
            return goDown();
        }
        int i = (sr * N) + sc;
        for (; i < N * H; i++) {
            int r = i / N;
            int c = i % N;

            if (c == N - 1) {
                continue;
            }

            if (loader[r][c] == 0) {
                if (loader[r][c + 1] == 0) {
                    loader[r][c] = 1;
                    loader[r][c + 1] = -1;
                    if (addBridge(bridgeCnt + 1, wholeBridge, r, c)) {
                        return true;
                    }
                    loader[r][c] = 0;
                    loader[r][c + 1] = 0;
                }
            }
        }
        return false;
    }

    public static boolean goDown() {
        for (int i = 0; i < N; i++) {
            int curPos = i;
            for (int j = 0; j < H; j++) {
                curPos += loader[j][curPos];
            }
            if (curPos != i) {
                return false;
            }
        }
        return true;
    }
}
