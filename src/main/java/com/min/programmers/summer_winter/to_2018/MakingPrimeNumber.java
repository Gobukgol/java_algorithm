package com.min.programmers.summer_winter.to_2018;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;

public class MakingPrimeNumber {
    static class Solution {
        static int answer = 0;

        public int solution(int[] nums) {
            combination(new boolean[nums.length], nums, 0, 0);
            return answer;
        }

        public void combination(boolean[] visited, int[] nums, int index, int count) {

            if (count == 3) {
                int sum = 0;
                for (int i = 0; i < nums.length; i++) {
                    if (visited[i]) {
                        sum += nums[i];
                    }
                }
                if (isPrimeNumber(sum)) {
                    answer++;
                }

                return;
            }

            for (int i = index; i < nums.length; i++) {
                visited[i] = true;
                combination(visited, nums, i + 1, count + 1);
                visited[i] = false;
            }
        }

        public boolean isPrimeNumber(int number) {
            for (int i = 2; i <= number / 2; i++) {
                if (number % i == 0) {
                    return false;
                }
            }

            return true;
        }
    }
}
