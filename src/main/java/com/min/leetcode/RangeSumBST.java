package com.min.leetcode;

public class RangeSumBST {

    public int rangeSumBST(TreeNode root, int low, int high) {
        int answer = 0;

        answer += checkNode(root, low, high);

        return answer;
    }

    public int checkNode(TreeNode node, int low, int high) {
        int val = 0;

        if (node == null) {
            return 0;
        }

        if (node.val >= low && node.val <= high) {
            val = node.val;
        }

        if (node.val == low || node.val < low) {
            val += checkNode(node.right, low, high);
        } else if (node.val == high || node.val > high) {
            val += checkNode(node.left, low, high);
        } else if (node.val > low && node.val < high) {
            val += checkNode(node.left, low, high);
            val += checkNode(node.right, low, high);
        }

        return val;
    }

    private class TreeNode {
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
