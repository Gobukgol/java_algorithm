package main.java.com.min.programmers.kakao.winterinternship;

import netscape.security.UserTarget;

import java.util.HashMap;
import java.util.Map;

public class StampingStones {

    static class Solution {
        public int solution(int[] stones, int k) {
            int answer = 0;

            int max = 200000000;
            int min = 0;

            while (max >= min) {
                int mid = (max + min) / 2;
                if (binarySearch(mid, stones, k)) {
                    answer = Math.max(answer, mid);
                    min = mid + 1;
                } else {
                    max = mid - 1;
                }
            }

            return answer;
        }

        public boolean binarySearch(int mid, int[] stones, int k) {
            int[] tmpStones = stones.clone();

            int zeroCount = 0;
            for (int i = 0; i < tmpStones.length; i++) {
                tmpStones[i] -= mid;
                if (tmpStones[i] < 0) {
                    zeroCount++;
                } else {
                    zeroCount = 0;
                }

                if (zeroCount == k) {
                    return false;
                }
            }

            return true;
        }
    }
}
