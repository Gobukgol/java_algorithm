package com.min.leetcode;

public class ComplementofBase10Integer {

    public static void main(String[] args) {
        bitwiseComplement(10);
    }

    public static int bitwiseComplement(int N) {
        String binary = Integer.toBinaryString(N);

        int answer = 0;
        for (int i = binary.length() - 1; i >= 0; i--) {
            char cur = binary.charAt(i);

            if (cur == '0') {
                answer += Math.pow(2, Math.abs(i - (binary.length() - 1)));
            }
        }

        return answer;
    }
}
