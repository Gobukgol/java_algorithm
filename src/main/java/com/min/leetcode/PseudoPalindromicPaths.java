package com.min.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PseudoPalindromicPaths {
    int answer = 0;

    public int pseudoPalindromicPaths(TreeNode root) {
        findAllPath(root, new ArrayList<>());
        return answer;
    }

    public void findAllPath(TreeNode node, List<Integer> path) {
        if (node.left == null && node.right == null) {
            path.add(node.val);
            if (isPalindrome(path)) {
                answer++;
            }
            path.remove(path.size() - 1);
            return;
        }

        if (node.left != null) {
            path.add(node.val);
            findAllPath(node.left, path);
            path.remove(path.size() - 1);
        }

        if (node.right != null) {
            path.add(node.val);
            findAllPath(node.right, path);
            path.remove(path.size() - 1);
        }
    }

    public boolean isPalindrome(List<Integer> path) {
        int cnt = 0;

        for (int i = 1; i < 10; i++) {
            if (Collections.frequency(path, i) % 2 == 1) {
                cnt++;
            }

            if (cnt > 1) {
                return false;
            }
        }

        return true;
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
