package com.min.leetcode;

public class SmallestIntegerDivisiblebyK {
    public int smallestRepunitDivByK(int K) {
        int mod = 0;

        for (int i = 1; i <= K; i++) {
            mod = (mod * 10 + 1) % K;

            if (mod == 0) {
                return i;
            }
        }

        return -1;
    }
}
