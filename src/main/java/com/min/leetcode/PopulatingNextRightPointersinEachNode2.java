package com.min.leetcode;

public class PopulatingNextRightPointersinEachNode2 {
    static int maxDepth = Integer.MIN_VALUE;

    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Node head = null;
        Node prev = null;
        Node curr = root;

        while (curr != null) {
            while (curr != null) {
                if (curr.left != null) {
                    if (head == null) {
                        head = curr.left;
                        prev = curr.left;
                    } else {
                        prev.next = curr.left;
                        prev = prev.next;
                    }
                }

                if (curr.right != null) {
                    if (head == null) {
                        head = curr.right;
                        prev = curr.right;
                    } else {
                        prev.next = curr.right;
                        prev = prev.next;
                    }
                }

                curr = curr.next;
            }

            curr = head;
            prev = null;
            head = null;
        }

        return root;
    }


    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    ;
}
