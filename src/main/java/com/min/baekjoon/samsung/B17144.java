package main.java.com.min.baekjoon.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class B17144 {
    static int[][] office;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int R, C, T;
    static List<Dust> dustList;
    static int[] air;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        office = new int[R][C];
        dustList = new ArrayList<>();
        air = new int[2];
        boolean airCheck = true;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int state = Integer.parseInt(st.nextToken());
                if (state == -1) {
                    if (airCheck) {
                        air[0] = i;
                        air[1] = j;
                        airCheck = false;
                    }
                }
                office[i][j] = state;
            }
        }

        for (int i = 0; i < T; i++) {
            cycle();
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                answer += office[i][j];
            }
        }
        answer += 2;
        System.out.println(answer);
    }

    public static void cycle() {
        //확산
        Queue<Dust> queue = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (office[i][j] > 0) {
                    queue.add(Dust.valueOf(i, j, office[i][j]));
                }
            }
        }

        while (!queue.isEmpty()) {
            Dust dust = queue.poll();
            int spreadAmt = dust.amount / 5;
            int spreadCnt = 0;

            for (int j = 0; j < 4; j++) {
                int tR = dust.r + dr[j];
                int tC = dust.c + dc[j];

                if (tR < 0 || tR >= R || tC < 0 || tC >= C) {
                    continue;
                }
                if (office[tR][tC] == -1) {
                    continue;
                }
                office[tR][tC] += spreadAmt;
                spreadCnt++;
            }

            office[dust.r][dust.c] -= spreadAmt * spreadCnt;
        }

        int[][] copy = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int r = i;
                int c = j;

                if (office[r][c] == -1) {
                    copy[i][j] = -1;
                    continue;
                }
                if (r > 0 && c > 0 && r < air[0] && c < C - 1) {
                    copy[i][j] = office[i][j];
                    continue;
                }
                if (r > air[0] + 1 && c > 0 && r < R - 1 && c < C - 1) {
                    copy[i][j] = office[i][j];
                    continue;
                }

                if (air[0] >= i) { //반시계
                    if (i == air[0]) {
                        if (j == C - 1) {
                            r -= 1;
                        } else {
                            c += 1;
                        }
                    } else if (i == 0) {
                        if (j == 0) {
                            r += 1;
                        } else {
                            c -= 1;
                        }
                    } else if (j == 0) {
                        r += 1;
                    } else {
                        r -= 1;
                    }
                } else { //시계
                    if (i == air[0] + 1) {
                        if (j == C - 1) {
                            r += 1;
                        } else {
                            c += 1;
                        }
                    } else if (i == R - 1) {
                        if (j == 0) {
                            r -= 1;
                        } else {
                            c -= 1;
                        }
                    } else if (j == 0) {
                        r -= 1;
                    } else {
                        r += 1;
                    }
                }
                if (copy[r][c] != -1) {
                    copy[r][c] = office[i][j];
                }
            }
        }

        for (int i = 0; i < R; i++) {
            office[i] = copy[i].clone();
        }

    }


    static class Dust {
        int r;
        int c;
        int amount;

        public Dust(int r, int c, int amount) {
            this.r = r;
            this.c = c;
            this.amount = amount;
        }

        public static Dust valueOf(int r, int c, int amount) {
            return new Dust(r, c, amount);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Dust)) return false;
            Dust dust = (Dust) o;
            return r == dust.r &&
                    c == dust.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }
}
