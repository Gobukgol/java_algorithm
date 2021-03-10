package com.min.leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class IntToRoman {

    public String intToRoman(int num) {
        StringBuilder answer = new StringBuilder();
        Map<Integer, String> roman = getRomanMap();
        Set<Integer> numbers = roman.keySet();

        while (num != 0) {
            for (int number : numbers) {
                if (num - number >= 0) {
                    answer.append(roman.get(number));
                    num -= number;
                    break;
                }
            }

        }

        return answer.toString();
    }

    private Map<Integer, String> getRomanMap() {
        Map<Integer, String> roman = new LinkedHashMap<>();

        roman.put(1000, "M");
        roman.put(900, "CM");
        roman.put(500, "D");
        roman.put(400, "CD");
        roman.put(100, "C");
        roman.put(90, "XC");
        roman.put(50, "L");
        roman.put(40, "XL");
        roman.put(10, "X");
        roman.put(9, "IX");
        roman.put(5, "V");
        roman.put(4, "IV");
        roman.put(1, "I");

        return roman;
    }
}
