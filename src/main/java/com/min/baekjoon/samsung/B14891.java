package com.min.baekjoon.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14891 {
    static char[][] gears = new char[4][8];
    static int[][] gearContact = new int[4][2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 4; i++) {
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                gears[i][j] = line.charAt(j);
            }
            for (int j = 0; j < 2; j++) {
                gearContact[i][j] = j * 4 + 2;
            }
        }

        int rotate, targetGear, dir;
        rotate = Integer.parseInt(br.readLine());

        for (int i = 0; i < rotate; i++) {
            st = new StringTokenizer(br.readLine());
            targetGear = Integer.parseInt(st.nextToken()) - 1;
            dir = Integer.parseInt(st.nextToken());

            rotate(targetGear, -1, dir);
        }

//        for(int i = 0 ; i <4 ; i++) {
//                System.out.println(gearContact[i][0]+" : "+gearContact[i][1]);
//        }
        System.out.println(getAnswer());
    }

    public static void rotate(int target, int from, int dir) {
        int right = gearContact[target][0];
        int left = gearContact[target][1];
        int tmp;

        //일단 회전
        for (int i = 0; i < 2; i++) {
            int idx = gearContact[target][i] + (dir * -1);
            if (idx == -1) {
                idx = 7;
            } else if (idx == 8) {
                idx = 0;
            }

            gearContact[target][i] = idx;
        }

        for (int i = -1; i <= 1; i += 2) {
            int next = target + i;
            if (next == -1 || next == 4 || next == from) {
                continue;
            }
            if (next < target) {
                tmp = gearContact[next][0];
                if (gears[next][tmp] != gears[target][left]) {
                    rotate(next, target, dir * -1);
                }
            } else {
                tmp = gearContact[next][1];
                if (gears[next][tmp] != gears[target][right]) {
                    rotate(next, target, dir * -1);
                }
            }
        }

    }

    public static int getAnswer() {
        int answer = 0;

        for (int i = 0; i < 4; i++) {
            int tmp = gearContact[i][0] - 2;
            if (tmp < 0) {
                tmp += 8;
            }

            if (gears[i][tmp] == '1') {
                answer += Math.pow(2, i);
            }
        }

        return answer;
    }
}
