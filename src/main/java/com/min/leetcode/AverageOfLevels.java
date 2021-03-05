package com.min.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

public class AverageOfLevels {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> answer = new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();

        q.add(root);

        double sum;
        int count;

        while (!q.isEmpty()) {
            Queue<TreeNode> temp = new LinkedList<>();

            sum = 0;
            count = 0;

            while (!q.isEmpty()) {
                TreeNode cur = q.poll();

                sum += cur.val;
                count++;

                if (cur.left != null) {
                    temp.add(cur.left);
                }

                if (cur.right != null) {
                    temp.add(cur.right);
                }
            }

            q = temp;
            answer.add(sum / count);
        }

        return answer;
    }

    public static class TreeNode {
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
