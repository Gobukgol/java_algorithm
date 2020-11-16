package com.min.programmers.summer_winter.to_2018;

public class CookieBuy {
    static class Solution {
        public int solution(int[] cookie) {
            int answer = 0;

            int[] sum = new int[cookie.length];

            sum[0] = cookie[0];

            for (int i = 1; i < cookie.length; i++) {
                sum[i] = sum[i - 1] + cookie[i];
            }

            for (int i = 0; i < cookie.length; i++) {
                for (int j = i + 1; j < cookie.length; j++) {
                    int right = sum[j] - sum[i];

                    if (right == sum[i]) {
                        answer = Math.max(answer, sum[i]);
                    }

                    if (right > sum[i] || answer > right) {
                        continue;
                    }

                    for (int k = 0; k < i; k++) {
                        int left = sum[i] - sum[k];
                        if (right == left) {
                            answer = Math.max(answer, left);
                        }
                    }
                }
            }

            return answer;
        }
    }
}
