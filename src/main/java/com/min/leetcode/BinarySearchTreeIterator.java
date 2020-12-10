package com.min.leetcode;

import java.util.PriorityQueue;
import java.util.Queue;

public class BinarySearchTreeIterator {
    class BSTIterator {
        private Queue<Integer> queue;

        public BSTIterator(TreeNode root) {
            queue = initQueue(root);
        }

        public int next() {
            return queue.poll();
        }

        public boolean hasNext() {
            return !queue.isEmpty();
        }

        private Queue<Integer> initQueue(TreeNode node) {
            Queue<Integer> queue = new PriorityQueue<>();

            search(node, queue);

            return queue;
        }

        private void search(TreeNode node, Queue<Integer> queue) {
            if (node == null) {
                return;
            }

            queue.add(node.val);
            search(node.left, queue);
            search(node.right, queue);
        }
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
