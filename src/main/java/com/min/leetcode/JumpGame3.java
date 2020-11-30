package com.min.leetcode;

public class JumpGame3 {
    static boolean result = false;

    public static void main(String[] args) {
        System.out.println(canReach(new int[]{3, 0, 2, 1, 2}, 2));
    }

    public static boolean canReach(int[] arr, int start) {
        return dfs(arr, start, new boolean[arr.length]);
    }

    public static boolean dfs(int[] arr, int curIdx, boolean[] visited) {
        if (curIdx < 0 || curIdx >= arr.length) {
            return false;
        }

        if (visited[curIdx]) {
            return false;
        }
        visited[curIdx] = true;

        if (arr[curIdx] == 0) {
            return true;
        }

        boolean result1 = dfs(arr, curIdx + arr[curIdx], visited);
        boolean result2 = dfs(arr, curIdx - arr[curIdx], visited);

        visited[curIdx] = false;

        return result1 || result2;
    }
}
