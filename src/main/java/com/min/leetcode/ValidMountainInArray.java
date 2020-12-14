package com.min.leetcode;

public class ValidMountainInArray {
    public boolean validMountainArray(int[] arr) {

        if (arr.length < 3) {
            return false;
        }

        int before = arr[0];
        int idx = 0;

        for (int i = 1; i < arr.length; i++) {
            if (before < arr[i]) {
                before = arr[i];
            } else if (before > arr[i]) {
                idx = i - 1;
                break;
            } else {
                return false;
            }
        }

        before = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            if (before < arr[i]) {
                before = arr[i];
            } else if (before > arr[i]) {
                if (idx == i + 1) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        return false;
    }
}
