package com.min.leetcode;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> answer = new ArrayList<>();
            List<Integer> temp = new ArrayList<>();

            backtrack(candidates, 0, answer, temp, target);

            return answer;
        }

        private void backtrack(int[] candidates, int idx, List<List<Integer>> answer, List<Integer> temp, int remain) {
            if (remain < 0) {
                return;
            }

            if (remain == 0) {
                answer.add(new ArrayList<>(temp));
                return;
            }

            for (int i = idx; i < candidates.length; i++) {
                temp.add(candidates[i]);
                backtrack(candidates, i, answer, temp, remain - candidates[i]);
                temp.remove(temp.size() - 1);
            }
        }
    }
}
