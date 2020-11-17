package com.min.leetcode;

public class LongestMountainInArray {

    public static void main(String[] args) {
        System.out.println(longestMountain(new int[]{2, 3, 3, 2, 0, 2}));
    }

    public static int longestMountain(int[] A) {
        int answer = 0;
        int length = 1;
        boolean isUpside = true;
        for (int i = 0; i < A.length - 1; i++) {
            if (isUpside) {
                if (A[i] < A[i + 1]) {
                    length++;
                } else if (length > 1 && A[i] > A[i + 1]) {
                    length++;
                    isUpside = false;
                } else if (A[i] == A[i + 1]) {
                    length = 1;
                }
            } else {
                if (A[i] > A[i + 1]) {
                    length++;
                } else if (A[i] < A[i + 1]) {
                    isUpside = true;
                    answer = Math.max(answer, length);
                    length = 2;
                } else if (A[i] == A[i + 1]) {
                    length = 1;
                    isUpside = true;
                }
            }

            if (!isUpside) {
                answer = Math.max(answer, length);
            }
        }

        return answer;
    }
}
