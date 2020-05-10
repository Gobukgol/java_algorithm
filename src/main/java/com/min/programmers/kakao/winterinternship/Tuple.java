package main.java.com.min.programmers.kakao.winterinternship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tuple {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] answer = solution.solution("{{100000}}");

        System.out.println(Arrays.toString(answer));
    }

    static class Solution {
        Pattern pattern = Pattern.compile("\\{(.*?)\\}");

        public int[] solution(String s) {
            List<Integer> answer = new ArrayList<>();

            s = s.substring(1, s.length() - 1);

            Matcher matcher = pattern.matcher(s);

            String[] group = new String[501];

            while (matcher.find()) {
                String tmp = matcher.group();
                tmp = tmp.substring(1, tmp.length() - 1);
                group[tmp.split(",").length] = tmp;
            }

            int i = 1;
            int totalSum = 0;
            while (i < 501 && group[i] != null) {
                String[] splits = group[i].split(",");
                int tmpSum = 0;
                for (int j = 0; j < splits.length; j++) {
                    tmpSum += Integer.parseInt(splits[j]);
                }
                answer.add(tmpSum - totalSum);
                totalSum = tmpSum;
                i++;
            }

            return answer.stream()
                    .mapToInt(value -> value)
                    .toArray();
        }
    }
}
