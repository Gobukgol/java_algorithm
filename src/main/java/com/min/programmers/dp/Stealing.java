package main.java.com.min.programmers.dp;

import java.util.Arrays;

public class Stealing {

    static class Solution {
        public int solution(int[] money) {
            int answer = 0;
            int houseCnt = money.length;
            int[] first = new int[houseCnt];
            int[] last = new int[houseCnt];

            first[0] = money[0];
            first[1] = Math.max(money[0], money[1]);

            last[0] = 0;
            last[1] = money[1];

            for (int i = 2; i < houseCnt - 1; i++) {
                first[i] = Math.max(first[i - 2] + money[i], first[i - 1]);
            }

            for (int i = 2; i < houseCnt; i++) {
                last[i] = Math.max(last[i - 2] + money[i], last[i - 1]);
            }
            Arrays.sort(first);
            Arrays.sort(last);
            answer = Math.max(first[houseCnt - 1], last[houseCnt - 1]);
            return answer;
        }


    }
}
