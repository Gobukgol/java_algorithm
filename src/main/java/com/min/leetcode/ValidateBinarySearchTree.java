package com.min.leetcode;

import java.util.Objects;

public class ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }

    public boolean validate(TreeNode root, Integer min, Integer max) {
        if (Objects.isNull(root)) {
            return true;
        }

        if ((Objects.nonNull(min) && root.val <= min) || (Objects.nonNull(max) && root.val >= max)) {
            return false;
        }

        return validate(root.left, min, root.val) && validate(root.right, root.val, max);
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
