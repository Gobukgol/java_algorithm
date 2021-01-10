package com.min.leetcode;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring(" "));
    }

    public static int lengthOfLongestSubstring(String s) {
        int answer = 0;
        int length = s.length();

        int i = 0, j = 0;

        Set<Character> chars = new HashSet<>();

        while (i < length && j < length) {
            if (!chars.contains(s.charAt(j))) {
                chars.add(s.charAt(j++));
                answer = Math.max(answer, j - i);
            } else {
                chars.remove(s.charAt(i++));
            }
        }

        return answer;
    }
}
