package com.min.baekjoon.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B15683 {
    static int N, M;
    static int[][] office;
    static int[] dr = {-1, 0, 1, 0}; //북동남서
    static int[] dc = {0, 1, 0, -1};
    static int answer = 0;
    static List<Pair<Integer, Integer>> cctvs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        office = new int[N][M];
        cctvs = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                office[i][j] = Integer.parseInt(st.nextToken());
                if (office[i][j] == 0) {
                    answer++;
                } else if (office[i][j] != 6) {
                    cctvs.add(Pair.of(i, j));
                }
            }
        }

        if (cctvs.size() == 0) {
            System.out.println(answer);
            return;
        }
        dfs(0, new int[cctvs.size()]);
        System.out.println(answer);
    }

    public static void dfs(int cctvCnt, int[] cctvDir) {
        if (cctvCnt == cctvs.size()) {
            answer = Math.min(answer, getAnswer(cctvDir));
            return;
        }

        Pair<Integer, Integer> cctv = cctvs.get(cctvCnt);

        if (office[cctv.left][cctv.right] == 2) {
            for (int i = 0; i < 2; i++) {
                cctvDir[cctvCnt] = i;
                dfs(cctvCnt + 1, cctvDir);
            }
        } else if (office[cctv.left][cctv.right] == 5) {
            cctvDir[cctvCnt] = 0;
            dfs(cctvCnt + 1, cctvDir);
        } else {
            for (int i = 0; i < 4; i++) {
                cctvDir[cctvCnt] = i;
                dfs(cctvCnt + 1, cctvDir);
            }
        }
    }

    public static int getAnswer(int[] cctvDir) {
        int result = 0;
        int[][] copy = copy(office);

        for (int i = 0; i < cctvDir.length; i++) {
            paint(cctvs.get(i), cctvDir[i], copy);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copy[i][j] == 0) {
                    result++;
                }
            }
        }
        return result;
    }

    public static void paint(Pair<Integer, Integer> cctv, int dir, int[][] office) {

        int cctvNum = office[cctv.left][cctv.right];
        int r = cctv.left;
        int c = cctv.right;
        switch (cctvNum) {
            case 1:
                while (true) {
                    r += dr[dir];
                    c += dc[dir];
                    if (r >= N || r < 0 || c >= M || c < 0) {
                        break;
                    } else if (office[r][c] == 6) {
                        break;
                    } else if (office[r][c] > 0 && office[r][c] < 6) {
                        continue;
                    } else {
                        office[r][c] = -1;
                    }
                }
                break;
            case 2:
                for (int i = dir; i < 4; i += 2) {
                    r = cctv.left;
                    c = cctv.right;
                    while (true) {
                        r += dr[i];
                        c += dc[i];
                        if (r >= N || r < 0 || c >= M || c < 0) {
                            break;
                        } else if (office[r][c] == 6) {
                            break;
                        } else if (office[r][c] > 0 && office[r][c] < 6) {
                            continue;
                        } else {
                            office[r][c] = -1;
                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < 2; i++) {
                    int tmpDir = dir + i;
                    if (tmpDir == 4) {
                        tmpDir = 0;
                    }
                    r = cctv.left;
                    c = cctv.right;
                    while (true) {
                        r += dr[tmpDir];
                        c += dc[tmpDir];
                        if (r >= N || r < 0 || c >= M || c < 0) {
                            break;
                        } else if (office[r][c] == 6) {
                            break;
                        } else if (office[r][c] > 0 && office[r][c] < 6) {
                            continue;
                        } else {
                            office[r][c] = -1;
                        }
                    }
                }
                break;
            case 4:
                for (int i = 0; i < 4; i++) {
                    if (i == dir) {
                        continue;
                    }
                    r = cctv.left;
                    c = cctv.right;
                    while (true) {
                        r += dr[i];
                        c += dc[i];
                        if (r >= N || r < 0 || c >= M || c < 0) {
                            break;
                        } else if (office[r][c] == 6) {
                            break;
                        } else if (office[r][c] > 0 && office[r][c] < 6) {
                            continue;
                        } else {
                            office[r][c] = -1;
                        }
                    }
                }
                break;
            case 5:
                for (int i = 0; i < 4; i++) {
                    r = cctv.left;
                    c = cctv.right;
                    while (true) {
                        r += dr[i];
                        c += dc[i];
                        if (r >= N || r < 0 || c >= M || c < 0) {
                            break;
                        } else if (office[r][c] == 6) {
                            break;
                        } else if (office[r][c] > 0 && office[r][c] < 6) {
                            continue;
                        } else {
                            office[r][c] = -1;
                        }
                    }
                }
                break;
        }
    }

    public static int[][] copy(int[][] source) {
        int n = source.length;
        int m = source[0].length;
        int[][] result = new int[n][m];

        for (int i = 0; i < n; i++) {
            System.arraycopy(source[i], 0, result[i], 0, source[i].length);
        }
        return result;
    }

    static class Pair<L, R> {
        final L left;
        final R right;

        public Pair(L left, R right) {
            this.left = left;
            this.right = right;
        }

        static <L, R> Pair<L, R> of(L left, R right) {
            return new Pair<L, R>(left, right);
        }
    }

}
