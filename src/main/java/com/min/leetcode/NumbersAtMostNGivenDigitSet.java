package com.min.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class NumbersAtMostNGivenDigitSet {
    static int answer = 0;

    public static void main(String[] args) {
        System.out.println(atMostNGivenDigitSet(new String[]{
                "1", "2", "3", "4", "7", "8", "9"}, 32901345));
    }

    public static int atMostNGivenDigitSet(String[] digits, int n) {
        String strN = String.valueOf(n);
        int nLength = strN.length();
        int[] dp = new int[nLength + 1];
        dp[nLength] = 1;

        for (int i = nLength - 1; i >= 0; --i) {
            // compute dp[i]
            int Si = strN.charAt(i) - '0';
            for (String d : digits) {
                if (Integer.parseInt(d) < Si)
                    dp[i] += Math.pow(digits.length, nLength - i - 1);
                else if (Integer.parseInt(d) == Si)
                    dp[i] += dp[i + 1];
            }
        }

        for (int i = 1; i < nLength; ++i)
            dp[0] += Math.pow(digits.length, i);
        return dp[0];
    }

}
