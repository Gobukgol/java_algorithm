package com.min.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CanFormArray {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        Set<String> strPieces = new HashSet<>();

        for (int[] piece : pieces) {
            strPieces.add(convert(piece));
        }

        List<String> numbers = new ArrayList<>();

        for (int num : arr) {
            numbers.add(String.valueOf(num));

            String temp = String.join(":", numbers);

            if (strPieces.contains(temp)) {
                numbers.clear();
            }
        }

        return numbers.size() == 0;
    }

    private String convert(int[] piece) {
        return Arrays.stream(piece)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(":"));
    }
}
