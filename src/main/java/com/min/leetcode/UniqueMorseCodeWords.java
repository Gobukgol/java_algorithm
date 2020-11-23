package com.min.leetcode;

import java.util.HashSet;
import java.util.Set;

public class UniqueMorseCodeWords {
    private static final String[] MORSE_CODE = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

    public int uniqueMorseRepresentations(String[] words) {
        Set<String> morseSet = new HashSet<>();

        for (String word : words) {
            morseSet.add(toMorseCode(word));
        }

        return morseSet.size();
    }

    public String toMorseCode(String word) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            stringBuilder.append(MORSE_CODE[word.charAt(i) - 'a']);
        }

        return stringBuilder.toString();
    }
}
