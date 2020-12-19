package com.min.leetcode;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class IncreasingTripletSubsequence {
    public boolean increasingTriplet(int[] nums) {
        int one = nums[0];
        int two = Integer.MAX_VALUE;

        if (nums.length < 3) {
            return false;
        }

        for (int num : nums) {
            if (num > two) {
                return true;
            }

            if (num > one) {
                two = num;
            }

            if (num < one) {
                one = num;
            }
        }

        return false;
    }
}
