package main.java.com.min.programmers.summer_winter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Origami {
    class Solution {
        public int[] solution(int n) {
            int[] conversion = {1, 0};

            List<Integer> result = new ArrayList<>();
            result.add(0);

            for (int i = 2; i <= n; i++) {
                result.add(0);
                int index = (int) Math.pow(2, i - 1) - 1;

                for (int j = index - 1; j >= 0; j--) {
                    result.add(conversion[result.get(j)]);
                }
            }

            int[] answer = new int[result.size()];

            for (int i = 0; i < result.size(); i++) {
                answer[i] = result.get(i);
            }

            return answer;
        }
    }
}
