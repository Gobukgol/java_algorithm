package com.min.leetcode;

import java.util.Objects;

public class HouseRobber3 {

    public int rob(TreeNode root) {
        int[] answer = robSubRoot(root);

        return Math.max(answer[0], answer[1]);
    }

    public int[] robSubRoot(TreeNode root) {
        if (root == null) {
            return new int[2];
        }

        int[] leftSub = robSubRoot(root.left);
        int[] rightSub = robSubRoot(root.right);

        int[] cur = new int[2];

        cur[0] = Math.max(leftSub[0], leftSub[1]) + Math.max(rightSub[0], rightSub[1]);
        cur[1] = root.val + leftSub[0] + rightSub[0];

        return cur;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
