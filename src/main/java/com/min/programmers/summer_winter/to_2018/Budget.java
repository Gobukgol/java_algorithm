package com.min.programmers.summer_winter.to_2018;

import java.util.Arrays;

public class Budget {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{2}, 1));
    }

    public static int solution(int[] d, int budget) {
        int answer = 0;

        Arrays.sort(d);

        int sum = 0;
        int i = 0;

        if (d.length == 1) {
            if (d[0] > budget) {
                return 0;
            }

            return 1;
        }

        for (; i < d.length; i++) {
            if (sum == budget) {
                break;
            } else if (sum > budget) {
                i--;
                break;
            }
            sum += d[i];
        }

        if (sum > budget) {
            i--;
        }

        return i;
    }
}
