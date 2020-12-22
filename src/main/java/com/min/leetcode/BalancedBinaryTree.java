package com.min.leetcode;

public class BalancedBinaryTree {

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int left = calcHeight(root.left);
        int right = calcHeight(root.right);

        if (Math.abs(left - right) > 1) {
            return false;
        }

        return isBalanced(root.left) && isBalanced(root.right);
    }

    public int calcHeight(TreeNode root) {
        if (root == null) {
            return 1;
        }
        return Math.max(calcHeight(root.left), calcHeight(root.right)) + 1;
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
