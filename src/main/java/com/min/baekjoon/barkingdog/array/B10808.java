package com.min.baekjoon.barkingdog.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class B10808 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String word = br.readLine();

        int[] alphabets = new int[26];

        for (int i = 0; i < word.length(); i++) {
            alphabets[word.charAt(i) - 'a']++;
        }

        for (int i = 0; i < alphabets.length; i++) {
            System.out.print(alphabets[i] + " ");
        }

        List<String> a = new ArrayList<>();

        a.forEach(System.out::println);
    }
}
