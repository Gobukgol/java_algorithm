package com.min.leetcode;

import java.util.Arrays;

public class SearchinRotatedSortedArray2 {
    public boolean search(int[] nums, int target) {
        return Arrays.stream(nums).anyMatch(num -> num == target);
    }
}
