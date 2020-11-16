package com.min.programmers.summer_winter.to_2018;

import java.util.HashSet;
import java.util.Set;

public class WordEnding {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[]{0, 0};

        Set<String> wordDic = new HashSet<>();

        wordDic.add(words[0]);

        for (int i = 1; i < words.length; i++) {
            int beforeSize = wordDic.size();
            if (words[i - 1].charAt(words[i - 1].length() - 1) != words[i].charAt(0)) {
                answer[0] = i % n + 1;
                answer[1] = i / n + 1;
                break;
            }

            wordDic.add(words[i]);

            if (beforeSize == wordDic.size()) {
                answer[0] = i % n + 1;
                answer[1] = i / n + 1;
                break;
            }
        }

        return answer;
    }
}
